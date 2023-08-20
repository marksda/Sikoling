package org.Sikoling.ejb.main.storage;

import java.io.IOException;
import java.io.InputStream;

import org.Sikoling.ejb.abstraction.repository.ILocalStorageRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class LocalStorageEJB implements ILocalStorageRepository {
	
	@Inject
	private LocalStorageImpl localStorageImpl;
	
	@Override
	public void upload(String fileKey, InputStream inputStream, String subPath) throws IOException {
		localStorageImpl.upload(fileKey, inputStream, subPath);
	}

	@Override
	public InputStream download(String subPath, String fileName) throws IOException {
		return localStorageImpl.download(subPath, fileName);
	}

	@Override
	public void delete(String fileName, String subPath) throws IOException {
		localStorageImpl.delete(fileName, subPath);
	}

}
