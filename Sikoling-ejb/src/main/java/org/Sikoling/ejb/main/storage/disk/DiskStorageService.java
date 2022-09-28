package org.Sikoling.ejb.main.storage.disk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
				);		
		if (!Files.exists(pathLocation)) {
			Files.createDirectories(pathLocation);
			pathLocation = Paths.get(
					this.rootPath
					.concat(File.separator)
					.concat(subPath)
					.concat(File.separator)
					.concat(fileKey)
				);
			Files.copy(inputStream, pathLocation, StandardCopyOption.REPLACE_EXISTING);
		}
		else {
			pathLocation = Paths.get(
					this.rootPath
					.concat(File.separator)
					.concat(subPath)
					.concat(File.separator)
					.concat(fileKey)
				);
			Files.copy(inputStream, pathLocation, StandardCopyOption.REPLACE_EXISTING);
		}
		
		
//		File targetFile = new File("src/main/resources/targetFile.tmp");
//
//	    FileUtils.copyInputStreamToFile(initialStream, targetFile);
		
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
