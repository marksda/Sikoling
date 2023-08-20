package org.Sikoling.ejb.abstraction.service.storage;

import java.io.IOException;
import java.io.InputStream;

import org.Sikoling.ejb.abstraction.repository.ILocalStorageRepository;

public class LocalStorageService implements ILocalStorageService {
	
	private final ILocalStorageRepository localStorageRepository;
	
	public LocalStorageService(ILocalStorageRepository localStorageRepository) {
		this.localStorageRepository = localStorageRepository;
	}

	@Override
	public void upload(String fileKey, InputStream inputStream, String subPath) throws IOException {
		localStorageRepository.upload(fileKey, inputStream, subPath);
	}

	@Override
	public InputStream download(String subPath, String fileName) throws IOException {
		return localStorageRepository.download(subPath, fileName);
	}

	@Override
	public void delete(String fileName, String subPath) throws IOException {
		localStorageRepository.delete(fileName, subPath);
	}

}
