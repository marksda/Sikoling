package org.Sikoling.ejb.abstraction.service.storage;

import java.io.IOException;
import java.io.InputStream;

public interface ILocalStorageService {
	void upload(String fileKey, InputStream inputStream, String subPath) throws IOException;
	InputStream download(String subPath, String fileName) throws IOException;
	void delete(String fileName, String subPath) throws IOException;
}
