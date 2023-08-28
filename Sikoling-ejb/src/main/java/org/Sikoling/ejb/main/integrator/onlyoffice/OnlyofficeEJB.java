package org.Sikoling.ejb.main.integrator.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
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
	public void commandRequest(RequestBodyPost requestBodyPost, String fileNameParam, String userAddress) throws Exception {
		onlyOfficeImpl.commandRequest(requestBodyPost, fileNameParam, userAddress);
	}

	@Override
	public FileModel getConfig(String namaFile, OnlyofficeUser onlyofficeUser) throws Exception {
		return onlyOfficeImpl.getConfig(namaFile, onlyofficeUser);
	}

}
