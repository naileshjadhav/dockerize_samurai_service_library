package com.zensar.autodeployment.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	private final Path root = Paths.get("uploads");

	/**
	 * Creates directory if not exists
	 */
	@Override
	public void init() {
		try {
			if (!Files.isDirectory(root))
				Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!" + e);
		}
	}

	/**
	 * Saves uploaded files to server
	 */
	@Override
	public long save(MultipartFile file) {
		try {
			return Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()),
					java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException("Could not copy the private key file. Error: ");
		}
	}

//	@Override
//	public Resource load(String filename) {
//		try {
//			Path file = root.resolve(filename);
//			Resource resource = new UrlResource(file.toUri());
//
//			if (resource.exists() || resource.isReadable()) {
//				return resource;
//			} else {
//				throw new RuntimeException("Could not read the file!");
//			}
//		} catch (MalformedURLException e) {
//			throw new RuntimeException("Error: " + e.getMessage());
//		}
//	}

}
