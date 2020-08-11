package com.zensar.SamuraiZenAnalyticaIntegration.service;

import java.util.List;

import com.zensar.SamuraiZenAnalyticaIntegration.model.SamuraiRpaDto;

public interface SamuraiRpaService {

	SamuraiRpaDto saveRpaRequest(SamuraiRpaDto dto);

	SamuraiRpaDto mergeRpaRequest(SamuraiRpaDto rpaDto3);

	List<SamuraiRpaDto> saveAllAnalyticaResponse(List<SamuraiRpaDto> rpaDto3);

	List<SamuraiRpaDto> getSamuraiRpaByEformIdAndEformStatus();

	SamuraiRpaDto findSamuraiRpaById(Long samuraiRpaId);

}