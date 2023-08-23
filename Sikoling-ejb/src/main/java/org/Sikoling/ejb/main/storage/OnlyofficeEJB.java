package org.Sikoling.ejb.main.storage;

import java.util.Properties;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
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

	@Override
	public FileModel getConfig(String namaFile, Properties properties) {
		return onlyOfficeImpl.getConfig(namaFile, properties);
	}

}
