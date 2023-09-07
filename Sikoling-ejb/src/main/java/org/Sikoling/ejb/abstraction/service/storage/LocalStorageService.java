package org.Sikoling.ejb.abstraction.service.storage;

import java.io.File;
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
	public File download(String fileNameParam) throws IOException {
		return localStorageRepository.download(fileNameParam);
	}

	@Override
	public void delete(String fileName, String subPath) throws IOException {
		localStorageRepository.delete(fileName, subPath);
	}
	
	@Override
	public void create(String fileKey, String subPath) throws IOException {
		localStorageRepository.create(fileKey, subPath);		
	}
	
	@Override
	public void move(String fileNameParamAsal, String fileNameParamTujuan) throws IOException {
		localStorageRepository.move(fileNameParamAsal, fileNameParamTujuan);
	}

	
	@Override
	public void moveDir(String directoryAsal, String directoryTujuan) throws IOException {
		localStorageRepository.moveDir(directoryAsal, directoryTujuan);
	}

}
