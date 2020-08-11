package com.zensar.service.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.service.library.entity.SubCategory;

@Repository
public interface SubCategoryRespository extends CrudRepository<SubCategory, Long> {

	Optional<SubCategory> findBysubCategoryName(String subCategoryName);

}
