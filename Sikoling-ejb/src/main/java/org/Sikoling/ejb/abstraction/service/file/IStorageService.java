package org.Sikoling.ejb.abstraction.service.file;

import java.io.IOException;
import java.io.InputStream;

public interface IStorageService {
	String save(String fileName, InputStream inputStream, String subPath) throws IOException;
    InputStream load(String fileName) throws IOException;
}
