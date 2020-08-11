package com.zensar.SamuraiZenAnalyticaIntegration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartInfo {

	private String ReleaseKey;
	private Integer RobotIds[];
	private Integer NoOfRobots;
	private String Strategy;
	private String access_token;

	public StartInfo() {
	}

	@JsonProperty("RobotIds")
	public Integer[] getRobotIds() {
		return RobotIds;
	}

	public void setRobotIds(Integer[] robotIds) {
		RobotIds = robotIds;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@JsonProperty("ReleaseKey")
	public String getReleaseKey() {
		return ReleaseKey;
	}

	public void setReleaseKey(String releaseKey) {
		ReleaseKey = releaseKey;
	}

	@JsonProperty("NoOfRobots")
	public Integer getNoOfRobots() {
		return NoOfRobots;
	}

	public void setNoOfRobots(Integer noOfRobots) {
		NoOfRobots = noOfRobots;
	}

	@JsonProperty("Strategy")
	public String getStrategy() {
		return Strategy;
	}

	public void setStrategy(String strategy) {
		Strategy = strategy;
	}

}
