package org.Sikoling.ejb.abstraction.repository;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;

public interface IOnlyOfficeRepository {
	void commandRequest(RequestBodyPost requestBodyPost) throws Exception;
}
