package org.Sikoling.ejb.abstraction.service.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;

public interface IOnlyofficeService {
	void commandRequest(RequestBodyPost requestBodyPost) throws Exception;
	FileModel getConfig(String fileNameParam, OnlyofficeUser onlyofficeUser) throws Exception;
}
