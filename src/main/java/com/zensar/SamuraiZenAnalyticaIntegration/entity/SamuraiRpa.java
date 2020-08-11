package com.zensar.SamuraiZenAnalyticaIntegration.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "samurai_rpa")
public class SamuraiRpa {
	@Id
	@Column(name = "samurai_rpa_id", length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long samuraiRpaId;

	@Column(name = "zeva_request_id", length = 20)
	private String zevaRequestId;

	@Column(name = "user_name", length = 20)
	private String userName;

	@Column(name = "user_email", length = 20)
	private String userEmail;

	@Column(name = "request_date_time")
	private LocalDateTime requestDateTime;

	@Column(name = "severity", length = 10)
	private String severity;

	@Column(name = "impact", length = 20)
	private String impact;

	@Column(name = "eform_id", length = 20)
	private Long eformId;

	@Column(name = "type_of_request", length = 20)
	private String typeOfRequest;

	@Column(name = "eform_status_by_platform", length = 50)
	private String eformStatusByPlatform;

	@Column(name = "eform_status_update_date")
	private LocalDateTime eformStatusUpdateDate;

	@Column(name = "platform_remarks", length = 100)
	private String platformRemarks;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "samurai_rpa_id")
	private List<SamuraiAnalytica> analytica = new ArrayList<SamuraiAnalytica>();

	public boolean addAnalytica(SamuraiAnalytica analytica) {
		return this.analytica.add(analytica);
	}

	public List<SamuraiAnalytica> getAnalytica() {
		return this.analytica;
	}

	public void setAnalytica(List<SamuraiAnalytica> analytica) {
		this.analytica = analytica;
	}

	public Long getSamuraiRpaId() {
		return this.samuraiRpaId;
	}

	public void setSamuraiRpaId(Long samuraiRpaId) {
		this.samuraiRpaId = samuraiRpaId;
	}

	public String getZevaRequestId() {
		return this.zevaRequestId;
	}

	public void setZevaRequestId(String zevaRequestId) {
		this.zevaRequestId = zevaRequestId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDateTime getRequestDateTime() {
		return this.requestDateTime;
	}

	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getImpact() {
		return this.impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public Long getEformId() {
		return this.eformId;
	}

	public void setEformId(Long eformId) {
		this.eformId = eformId;
	}

	public String getTypeOfRequest() {
		return this.typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public String getEformStatusByPlatform() {
		return this.eformStatusByPlatform;
	}

	public void setEformStatusByPlatform(String eformStatusByPlatform) {
		this.eformStatusByPlatform = eformStatusByPlatform;
	}

	public LocalDateTime getEformStatusUpdateDate() {
		return this.eformStatusUpdateDate;
	}

	public void setEformStatusUpdateDate(LocalDateTime eformStatusUpdateDate) {
		this.eformStatusUpdateDate = eformStatusUpdateDate;
	}

	public String getPlatformRemarks() {
		return this.platformRemarks;
	}

	public void setPlatformRemarks(String platformRemarks) {
		this.platformRemarks = platformRemarks;
	}
}
