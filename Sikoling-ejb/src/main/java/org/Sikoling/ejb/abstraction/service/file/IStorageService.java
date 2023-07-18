package org.Sikoling.ejb.abstraction.service.file;

import java.io.IOException;
import java.io.InputStream;

public interface IStorageService {
	Boolean save(String fileName, InputStream inputStream, String subPath) throws IOException;
	Boolean delete(String fileName, String subPath) throws IOException;
    InputStream load(String subPath, String fileName) throws IOException;
}
