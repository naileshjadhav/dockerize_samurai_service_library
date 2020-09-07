package com.zensar.autodeployment.model;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class AzureDeploymentDto {
	@NonNull
	private String hostAddress;
	@NonNull
	private String hostUserName;
	@NonNull
	private String hostPassword;
}
