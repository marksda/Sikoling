package org.Sikoling.ejb.main.storage.disk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.Sikoling.ejb.abstraction.service.file.IStorageService;

public class DiskStorageService implements IStorageService {
	private final String rootPath;

	public DiskStorageService(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public String save(String fileName, InputStream inputStream, String subPath) throws IOException {
		String fileKey = UUID.randomUUID().toString() + "-" + fileName;
		Path pathLocation = Paths.get(
					this.rootPath
					.concat(File.separator)
					.concat(subPath)
					.concat(File.separator)
					.concat(fileKey)
				);
		Files.copy(inputStream, pathLocation);
		
		return fileKey;
	}

	@Override
	public InputStream load(String subPath, String fileName) throws IOException {
		Path pathLocation = Paths.get(
					this.rootPath
					.concat(File.separator)
					.concat(subPath)
					.concat(File.separator)
					.concat(fileName)
				);
		
		return Files.newInputStream(pathLocation);
	}
	
}
