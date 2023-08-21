package org.Sikoling.ejb.abstraction.service.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;

public interface IOnlyofficeService {
	void commandRequest(RequestBodyPost requestBodyPost) throws Exception;
}
