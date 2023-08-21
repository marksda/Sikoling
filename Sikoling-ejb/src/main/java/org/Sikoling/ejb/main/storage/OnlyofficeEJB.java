package org.Sikoling.ejb.main.storage;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class OnlyofficeEJB implements IOnlyOfficeRepository {
	
	@Inject
	private OnlyOfficeImpl onlyOfficeImpl; 

	@Override
	public void commandRequest(RequestBodyPost requestBodyPost) throws Exception {
		onlyOfficeImpl.commandRequest(requestBodyPost);
	}

}
