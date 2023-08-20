package org.Sikoling.ejb.main.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.Sikoling.ejb.abstraction.repository.ILocalStorageRepository;

public class LocalStorageImpl implements ILocalStorageRepository {
	private final String rootPath;

	public LocalStorageImpl(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public void upload(String fileKey, InputStream inputStream, String subPath) throws IOException {
		try {
			Path pathLocation = Paths.get(rootPath.concat(File.separator).concat(subPath));		
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
		} catch (Exception e) {
			throw new IOException("upload file gagal");
		}		
	}

	@Override
	public InputStream download(String subPath, String fileName) throws IOException {
		Path pathLocation = Paths.get(
				this.rootPath
				.concat(File.separator)
				.concat(subPath)
				.concat(File.separator)
				.concat(fileName)
			);
	
		return Files.newInputStream(pathLocation);
	}

	@Override
	public void delete(String fileName, String subPath) throws IOException {
		try {
			File file = new File(rootPath.concat(File.separator).concat(subPath).concat(File.separator).concat(fileName));
			file.delete();
		} catch (Exception e) {
			throw new IOException("Delete file gagal");
		}
	}

}
