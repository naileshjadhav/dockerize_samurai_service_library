package com.zensar.SamuraiZenAnalyticaIntegration.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zensar.SamuraiZenAnalyticaIntegration.model.RpaDto;
import com.zensar.SamuraiZenAnalyticaIntegration.model.SamuraiAnalyticaDto;
import com.zensar.SamuraiZenAnalyticaIntegration.model.SamuraiRpaDto;
import com.zensar.SamuraiZenAnalyticaIntegration.model.StartInfo;
import com.zensar.SamuraiZenAnalyticaIntegration.model.UserRatingDto;
import com.zensar.SamuraiZenAnalyticaIntegration.model.WrapperBotDto;
import com.zensar.SamuraiZenAnalyticaIntegration.service.SamuraiRpaService;

@RestController
public class SamuraiZenanalyticaIntegrationController {

	private static final Logger log = LoggerFactory.getLogger(SamuraiZenanalyticaIntegrationController.class);

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	SamuraiRpaService service;
	@Value("${analyticaUri}")
	private String analyticaUri;
	@Value("${ratingUpdateUri}")
	private String ratingUpdateUri;
	@Value("${botTriggerUri}")
	private String botTriggerUrl;
	@Value("${generateTokenUri}")
	private String generateTokenUrl;
	@Value("${generateReleaseKey}")
	private String generateReleaseKeyUrl;

	private static final String grantType = "grant_type";
	private static final String grantTypeValue = "refresh_token";
	private static final String clientId = "client_id";
	private static final String clientIdValue = "8DEv1AMNXczW3y4U15LL3jYf62jK93n5";
	private static final String refreshToken = "refresh_token";
	private static final String refreshTokenVAlue = "FqcUTkoDd8TTg8U8tcf742XisoIyu2o36-ZS5PAF-majT";
	private static String releaseKey = "";
	private static String bearerToken = "";
	private static final String EFORM_BOT = "UI_EFORM_BOT";
	private static final String EFORM_STATUS_BY_PLATFORM = "Open";
	private static final String PLATFORM_REMARK = "Pending";
	private static final String contentType = "content-type";
	private static final String contentTypeValue = "application/json";
	private static final String authorization = "authorization";
	private static final String bearer = "Bearer ";
	private static final String uipathFolderpath = "x-uipath-folderpath";
	private static final String uipathTenantname = "x-uipath-tenantname";
	private static final String uipathFolderpathValue = "Default";
	private static final String uipathTenantnameValue = "ZenDefaultvvzg306607";

	@PostMapping(value = "/analytica", consumes = "application/json", produces = "application/json")
	public ResponseEntity<SamuraiRpaDto> samuraiAnalyticaRpaIntegration(@Validated @RequestBody SamuraiRpaDto rpaDto)
			throws Exception {

		log.info("Started rpa call...........");
		List<SamuraiAnalyticaDto> rpaDto3 = new ArrayList<SamuraiAnalyticaDto>();
		rpaDto.setRequestDateTime(LocalDateTime.now());
		if (rpaDto.getEformId() != null && rpaDto.getEformId() != 0)
			rpaDto.setEformStatusByPlatform(EFORM_STATUS_BY_PLATFORM);
		rpaDto.setPlatformRemarks(PLATFORM_REMARK);
		SamuraiRpaDto rpaDto2 = service.saveRpaRequest(rpaDto);

		HttpEntity<SamuraiRpaDto> requestEntity = new HttpEntity<SamuraiRpaDto>(rpaDto);
		ResponseEntity<SamuraiAnalyticaDto[]> response = restTemplate.exchange(analyticaUri, HttpMethod.POST,
				requestEntity, SamuraiAnalyticaDto[].class);
		log.info("Analytica response status::" + response.getStatusCodeValue());

		if (response.getStatusCodeValue() == 200) {
			rpaDto3 = Arrays.asList(response.getBody());
			log.info("Anlaytica response::" + rpaDto3);
			rpaDto2.setAnalyticaDtos(rpaDto3);
			rpaDto2 = service.saveRpaRequest(rpaDto2);
			if (rpaDto2.getEformStatusByPlatform() != null
					&& rpaDto2.getEformStatusByPlatform().equals(EFORM_STATUS_BY_PLATFORM)) {
				if (rpaDto2.getAnalyticaDtos().size() > 0
						&& rpaDto2.getAnalyticaDtos().get(0).getResolutionPlatform().equals(EFORM_BOT)) {
					triggerRpaBotProcess();
				}
			}
		}
		rpaDto2.setAnalyticaDtos(rpaDto3);
		log.info("Finished rpa call...........");
		return new ResponseEntity<SamuraiRpaDto>(rpaDto2, HttpStatus.OK);
	}

	@GetMapping(value = "/rpa")
	public ResponseEntity<List<RpaDto>> sendEformToRpa() {
		log.info("Starting sendEformToRpa...");

		List<SamuraiRpaDto> samuraiRpaDtos = service.getSamuraiRpaByEformIdAndEformStatus();
		List<RpaDto> rpaDtos = samuraiRpaDtos.stream()
				.map(e -> new RpaDto(e.getEformId(), e.getUserEmail(), e.getSamuraiRpaId()))
				.collect(Collectors.toList());
		rpaDtos.forEach(e -> log.info("Finished sendEformToRpa...{}", e.getSamuraiRpaId()));
		return new ResponseEntity<List<RpaDto>>(rpaDtos, HttpStatus.OK);
	}

	@PostMapping("/rpa")
	public ResponseEntity<Boolean> updateEformStatus(@RequestBody RpaDto dto) {
		log.info("Starting updateEformStatus..." + dto.toString());
		Boolean value = false;
		SamuraiRpaDto rpaDto = service.findSamuraiRpaById(dto.getSamuraiRpaId());
		rpaDto.setEformStatusByPlatform(dto.getEformStatusByRpa());
		rpaDto = service.mergeRpaRequest(rpaDto);
		value = true;
		log.info("Finished updateEformStatus...");
		return new ResponseEntity<Boolean>(value, HttpStatus.OK);
	}

	private void triggerRpaBotProcess() {
		bearerToken = generateBearerToken();
		releaseKey = generateReleaseKey();
		startBotJob();
	}

	private void startBotJob() {

		log.info("Start job processed.....");
		HttpHeaders headers = new HttpHeaders();
		headers.add(contentType, contentTypeValue);
		headers.add(authorization, bearer + bearerToken);
		headers.add(uipathFolderpath, uipathFolderpathValue);
		headers.add(uipathTenantname, uipathTenantnameValue);
		headers.add("user-agent", "zensar-ttg");

		StartInfo startInfoObject = new StartInfo();
		startInfoObject.setReleaseKey(releaseKey);
		startInfoObject.setNoOfRobots(0);
		Integer[] ids = {};
		startInfoObject.setRobotIds(ids);
		startInfoObject.setStrategy("All");

		WrapperBotDto wrapperBotDto = new WrapperBotDto();
		wrapperBotDto.setStartInfo(startInfoObject);
		ObjectMapper mapperObj = new ObjectMapper();
		mapperObj.setSerializationInclusion(Include.NON_NULL);
		String jsonStr = null;
		try {
			ObjectWriter objectWriter = mapperObj.writerFor(WrapperBotDto.class);
			jsonStr = objectWriter.writeValueAsString(wrapperBotDto);
			log.info("Json string :: " + jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpEntity<String> requestEntity = new HttpEntity<String>(jsonStr, headers);
		log.info("Request body :: " + requestEntity.getBody().toString());

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(botTriggerUrl, HttpMethod.POST, requestEntity, String.class);
		} catch (RestClientException e) {
			log.error("Exception while calling start rpa job::" + e);
			e.printStackTrace();
		}
		if (response != null)
			log.info("RPA trigger response status::" + response.getStatusCodeValue());
		log.info("Start job processed finished.....");
	}

	private String generateReleaseKey() {

		log.info("GenerateReleaseKey started...");
		log.info("Beaer token received to generateReleaseKey::" + bearerToken);

		HttpHeaders headers = new HttpHeaders();
		headers.add(contentType, contentTypeValue);
		headers.add(uipathTenantname, uipathTenantnameValue);
		headers.add(authorization, bearer + bearerToken);
		headers.add(uipathFolderpath, uipathFolderpathValue);
		headers.add("user-agent", "Samurai-ttg");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(headers);
		ResponseEntity<String> response = restTemplate.exchange(generateReleaseKeyUrl, HttpMethod.GET, requestEntity,
				String.class);
		log.info("generateReleaseKey response status code::" + response.getStatusCodeValue());
		ObjectMapper mapper = new ObjectMapper();
		try {
			releaseKey = mapper.readTree(response.getBody()).get("value").get(0).get("Key").asText();
			log.info("result::" + mapper.readTree(response.getBody()).get("value").get(0).get("Key"));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log.info("GenerateReleaseKey Finished...");
		return releaseKey;
	}

	private String generateBearerToken() {

		log.info("GenerateBearerToken started...");
		HttpHeaders headers = new HttpHeaders();
		headers.add(contentType, contentTypeValue);
		headers.add(uipathTenantname, uipathTenantnameValue);
		headers.add("user-agent", "Samurai-ttg");
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> value = new HashMap<String, Object>();
		value.put(grantType, grantTypeValue);
		value.put(clientId, clientIdValue);
		value.put(refreshToken, refreshTokenVAlue);

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(value, headers);

		ResponseEntity<StartInfo> response = restTemplate.exchange(generateTokenUrl, HttpMethod.POST, requestEntity,
				StartInfo.class);
		log.info("RPA generate token url call response status::" + response.getStatusCodeValue());
		log.info("RPA generate token url call response access_token::" + response.getBody().getAccess_token());
		log.info("GenerateBearerToken Finished...");
		return response.getBody().getAccess_token();
	}

	@PostMapping("/rate")
	public String updateUserRating(@RequestBody UserRatingDto dto) {
		log.info("User feedback request from ZEVA::" + dto);
		log.info("Started updateUserRating call..........." + dto.toString());
		if (dto.getFeedback() == 0) {
			dto.setFeedback(-1);
		}
		HttpEntity<UserRatingDto> requestEntity = new HttpEntity<UserRatingDto>(dto);
		ResponseEntity<String> response = restTemplate.exchange(ratingUpdateUri, HttpMethod.POST, requestEntity,
				String.class);
		log.info("updateUserRating response status::" + response.getStatusCodeValue());
		log.info("updateUserRating response body::" + response.getBody());
		return response.getBody();
	}
}