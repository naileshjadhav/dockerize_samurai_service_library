package com.zensar.SamuraiZenAnalyticaIntegration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "samurai_analytica")
public class SamuraiAnalytica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "samurai_analytica_id", length = 10)
	private Long samuraiAnalyticaId;
	@Column(name = "db_connection_url", length = 100)
	private String dbConnectionUrl;
	@Column(name = "solution_type", length = 50)
	private String solutionType;
	@Column(name = "resolution_platform", length = 100)
	private String resolutionPlatform;
	@Column(name = "resolution_response", length = 5000)
	private String resolutionResponse;
	@Column(name = "resolution_id", length = 10)
	private Long resolutionID;
	@Column(name = "resolution_rank", length = 10)
	private Long resolutionRank;
	@ManyToOne
	@JoinColumn(name = "samurai_rpa_id")
	@JsonBackReference
	private SamuraiRpa samuraiRpa;

	public SamuraiRpa getSamuraiRpa() {
		return samuraiRpa;
	}

	public void setSamuraiRpa(SamuraiRpa samuraiRpa) {
		this.samuraiRpa = samuraiRpa;
	}

	public Long getSamuraiAnalyticaId() {
		return samuraiAnalyticaId;
	}

	public void setSamuraiAnalyticaId(Long samuraiAnalyticaId) {
		this.samuraiAnalyticaId = samuraiAnalyticaId;
	}

	public Long getResolutionID() {
		return resolutionID;
	}

	public void setResolutionID(Long resolutionID) {
		this.resolutionID = resolutionID;
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

}
