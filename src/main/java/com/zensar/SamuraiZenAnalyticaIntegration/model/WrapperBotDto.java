package com.zensar.SamuraiZenAnalyticaIntegration.model;

public class WrapperBotDto {

	public WrapperBotDto() {
		super();
	}

	private StartInfo startInfo;

	public StartInfo getStartInfo() {
		return startInfo;
	}

	public void setStartInfo(StartInfo startInfo) {
		this.startInfo = startInfo;
	}

	@Override
	public String toString() {
		return "WrapperBotDto [startInfo=" + startInfo + "]";
	}

}
