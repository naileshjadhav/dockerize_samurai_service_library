package com.zensar.service.library.controller;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "installationGuide")
@CrossOrigin(origins = "*")
public class InstallationGuideController {
	private static final Logger log = LoggerFactory.getLogger(InstallationGuideController.class);

//	@GetMapping("/file/{fileName:.+}")
//	public ResponseEntity<?> downloadFileFromLocalDocker(@PathVariable String fileName) throws IOException {
//		String contents = "";
//		try (InputStream inputStream = getClass().getResourceAsStream("/" + fileName.toLowerCase());
//				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//			contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
//		}
//		log.info("Installation guide file path:" + contents);
//		// InputStreamResource resource = new InputStreamResource(new
//		// FileInputStream(file));
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(contents);
//	}

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<?> downloadFileFromLocal(@PathVariable String fileName) throws IOException {

		InputStream file = new ClassPathResource(fileName.toLowerCase()).getInputStream();
		log.info("Installation guide available :" + file.available());
		InputStreamResource resource = new InputStreamResource(file);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(resource);
	}

}
