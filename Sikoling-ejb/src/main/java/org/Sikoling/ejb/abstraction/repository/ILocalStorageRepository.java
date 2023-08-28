package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import java.io.InputStream;

public interface ILocalStorageRepository {
	void upload(String fileKey, InputStream inputStream, String subPath) throws IOException;
	void create(String fileKey, String subPath) throws IOException;
	InputStream download(String fileNameParam) throws IOException;
	void delete(String fileName, String subPath) throws IOException;
}
