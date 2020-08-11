package com.zensar.SamuraiZenAnalyticaIntegration.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.NotNull;


public class SamuraiRpaDto {

	@NotNull
	//@NotBlank
	private String event;
	private Long samuraiRpaId;
	@NotNull
	//@NotBlank
	private String zevaRequestId;
	private String userName;
	private String userEmail;
	private LocalDateTime requestDateTime;
	private String severity;
	private String impact;
	private Long eformId;
	@NotNull
	//@NotBlank
	private String typeOfRequest;
	private String eformStatusByPlatform;
	private LocalDateTime eformStatusUpdateDate;
	private String platformRemarks;
	private List<SamuraiAnalyticaDto> analyticaDtos = new ArrayList<SamuraiAnalyticaDto>();

	public SamuraiRpaDto() {
		super();
	}

	public SamuraiRpaDto(Long samuraiRpaId, String userEmail, Long eformId, String eformStatusByPlatform) {
		super();
		this.samuraiRpaId = samuraiRpaId;
		this.userEmail = userEmail;
		this.eformId = eformId;
		this.eformStatusByPlatform = eformStatusByPlatform;
	}

	public List<SamuraiAnalyticaDto> getAnalyticaDtos() {
		return analyticaDtos;
	}

	public void setAnalyticaDtos(List<SamuraiAnalyticaDto> analyticaDtos) {
		this.analyticaDtos = analyticaDtos;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getSamuraiRpaId() {
		return samuraiRpaId;
	}

	public void setSamuraiRpaId(Long samuraiRpaId) {
		this.samuraiRpaId = samuraiRpaId;
	}

	public String getZevaRequestId() {
		return zevaRequestId;
	}

	public void setZevaRequestId(String zevaRequestId) {
		this.zevaRequestId = zevaRequestId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public Long getEformId() {
		return eformId;
	}

	public void setEformId(Long eformId) {
		this.eformId = eformId;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public String getEformStatusByPlatform() {
		return eformStatusByPlatform;
	}

	public void setEformStatusByPlatform(String eformStatusByPlatform) {
		this.eformStatusByPlatform = eformStatusByPlatform;
	}

	public LocalDateTime getEformStatusUpdateDate() {
		return eformStatusUpdateDate;
	}

	public void setEformStatusUpdateDate(LocalDateTime eformStatusUpdateDate) {
		this.eformStatusUpdateDate = eformStatusUpdateDate;
	}

	public String getPlatformRemarks() {
		return platformRemarks;
	}

	public void setPlatformRemarks(String platformRemarks) {
		this.platformRemarks = platformRemarks;
	}

}
