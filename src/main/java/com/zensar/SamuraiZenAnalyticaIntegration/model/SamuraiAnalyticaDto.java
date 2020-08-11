package com.zensar.SamuraiZenAnalyticaIntegration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SamuraiAnalyticaDto {

	@JsonIgnore
	private Long samuraiAnalyticaId;
	private String dbConnectionUrl;
	private String solutionType;
	private String resolutionPlatform;
	private String resolutionResponse;
	private Long resolutionId;
	private Long resolutionRank;

	public Long getSamuraiAnalyticaId() {
		return samuraiAnalyticaId;
	}

	public void setSamuraiAnalyticaId(Long samuraiAnalyticaId) {
		this.samuraiAnalyticaId = samuraiAnalyticaId;
	}

	public Long getResolutionId() {
		return resolutionId;
	}

	public void setResolutionId(Long resolutionId) {
		this.resolutionId = resolutionId;
	}

	public Long getResolutionRank() {
		return resolutionRank;
	}

	public void setResolutionRank(Long resolutionRank) {
		this.resolutionRank = resolutionRank;
	}

	public String getResolutionResponse() {
		return resolutionResponse;
	}

	public void setResolutionResponse(String resolutionResponse) {
		this.resolutionResponse = resolutionResponse;
	}

	public String getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}

	public String getResolutionPlatform() {
		return resolutionPlatform;
	}

	public void setResolutionPlatform(String resolutionPlatform) {
		this.resolutionPlatform = resolutionPlatform;
	}

	public String getDbConnectionUrl() {
		return dbConnectionUrl;
	}

	public void setDbConnectionUrl(String dbConnectionUrl) {
		this.dbConnectionUrl = dbConnectionUrl;
	}

	@Override
	public String toString() {
		return "SamuraiAnalyticaDto [samuraiAnalyticaId=" + samuraiAnalyticaId + ", dbConnectionUrl=" + dbConnectionUrl
				+ ", solutionType=" + solutionType + ", resolutionPlatform=" + resolutionPlatform
				+ ", resolutionResponse=" + resolutionResponse + ", resolutionID=" + resolutionId + ", resolutionRank="
				+ resolutionRank + "]";
	}

	
}
