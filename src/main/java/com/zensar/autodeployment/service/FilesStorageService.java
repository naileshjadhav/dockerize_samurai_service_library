package com.zensar.autodeployment.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

	public void init();

	public long save(MultipartFile file);

	//public Resource load(String filename);

}
