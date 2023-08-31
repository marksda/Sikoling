package org.Sikoling.ejb.abstraction.service.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ILocalStorageService {
	void upload(String fileKey, InputStream inputStream, String subPath) throws IOException;
	void create(String fileKey, String subPath) throws IOException;
	File download(String fileNameParam) throws IOException;
	void delete(String fileName, String subPath) throws IOException;
}
