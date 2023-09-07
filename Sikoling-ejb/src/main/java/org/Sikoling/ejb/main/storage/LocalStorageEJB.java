package org.Sikoling.ejb.main.storage;

import java.io.File;
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
	public File download(String fileNameParam) throws IOException {
		return localStorageImpl.download(fileNameParam);
	}

	@Override
	public void delete(String fileName, String subPath) throws IOException {
		localStorageImpl.delete(fileName, subPath);
	}
	
	@Override
	public void create(String fileKey, String subPath) throws IOException {
		localStorageImpl.create(fileKey, subPath);		
	}
	
	@Override
	public void move(String fileNameParamAsal, String fileNameParamTujuan) throws IOException {
		localStorageImpl.move(fileNameParamAsal, fileNameParamTujuan);
	}

	@Override
	public void moveDir(String directoryAsal, String directoryTujuan) throws IOException {
		localStorageImpl.moveDir(directoryAsal, directoryTujuan);
	}

}
