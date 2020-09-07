package com.zensar.autodeployment.model;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwsDeploymentDto {
	@NonNull
	private String hostAddress;
	@NonNull
	private String hostUserName;
}
