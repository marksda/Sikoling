package org.Sikoling.ejb.abstraction.repository;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;

public interface IOnlyOfficeRepository {
	void commandRequest(RequestBodyPost requestBodyPost, String fileNameParam, String userAddress) throws Exception;
	FileModel getConfig(String namaFile, OnlyofficeUser onlyofficeUser) throws Exception;
}
