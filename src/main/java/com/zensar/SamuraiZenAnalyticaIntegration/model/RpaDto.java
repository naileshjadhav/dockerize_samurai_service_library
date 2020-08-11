package com.zensar.SamuraiZenAnalyticaIntegration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RpaDto {

	private Long eformId;
	private String eformStatusByRpa;
	private String userEmail;
	private Long samuraiRpaId;

	public RpaDto() {
		super();
	}

	public RpaDto(Long eformId, String userEmail, Long samuraiRpaId) {
		super();
		this.eformId = eformId;
		this.userEmail = userEmail;
		this.samuraiRpaId = samuraiRpaId;
	}

	public Long getSamuraiRpaId() {
		return samuraiRpaId;
	}

	public void setSamuraiRpaId(Long samuraiRpaId) {
		this.samuraiRpaId = samuraiRpaId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getEformId() {
		return eformId;
	}

	public void setEformId(Long eformId) {
		this.eformId = eformId;
	}

	public String getEformStatusByRpa() {
		return eformStatusByRpa;
	}

	public void setEformStatusByRpa(String eformStatusByRpa) {
		this.eformStatusByRpa = eformStatusByRpa;
	}

	@Override
	public String toString() {
		return "RpaDto [eformId=" + eformId + ", eformStatusByRpa=" + eformStatusByRpa + ", userEmail=" + userEmail
				+ ", samuraiRpaId=" + samuraiRpaId + "]";
	}

}
