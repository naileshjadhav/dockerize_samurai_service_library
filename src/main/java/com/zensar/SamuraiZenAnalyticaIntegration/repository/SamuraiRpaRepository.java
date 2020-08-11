package com.zensar.SamuraiZenAnalyticaIntegration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zensar.SamuraiZenAnalyticaIntegration.entity.SamuraiRpa;

public interface SamuraiRpaRepository extends CrudRepository<SamuraiRpa, Long> {

	@Query(value = "select rpa.* from samurai_rpa rpa join samurai_analytica sa where sa.samurai_rpa_id=rpa.samurai_rpa_id and rpa.eform_id is not null and sa.resolution_platform=:resolutionPlatform and rpa.eform_status_by_platform=:eformStatusByPlatform",nativeQuery = true)
	Optional<List<SamuraiRpa>> getSamuraiRpaByEformIdAndEformStatus(@Param("resolutionPlatform") String resolutionPlatform,@Param("eformStatusByPlatform") String eformStatusByPlatform);

}
