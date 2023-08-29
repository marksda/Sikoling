package org.Sikoling.ejb.abstraction.service.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import jakarta.json.JsonObject;

public interface IOnlyofficeService {
	void commandRequest(JsonObject requestBodyPost, String fileNameParam, String userAddress) throws Exception;
	FileModel getConfig(String namaFile, OnlyofficeUser onlyofficeUser) throws Exception;
}
