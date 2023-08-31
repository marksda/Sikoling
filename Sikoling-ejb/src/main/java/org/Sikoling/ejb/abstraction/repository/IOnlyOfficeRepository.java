package org.Sikoling.ejb.abstraction.repository;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import jakarta.json.JsonObject;

public interface IOnlyOfficeRepository {
	void commandRequest(JsonObject requestBodyPost, String fileNameParam, String userAddress) throws Exception;
	FileModel getConfig(String namaFile, String mode, String height, String width, OnlyofficeUser onlyofficeUser) throws Exception;
}
