package org.Sikoling.ejb.abstraction.service.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;

public class OnlyofficeService implements IOnlyofficeService {
	
	private final IOnlyOfficeRepository onlyOfficeRepository;

	public OnlyofficeService(IOnlyOfficeRepository onlyOfficeRepository) {
		this.onlyOfficeRepository = onlyOfficeRepository;
	}

	@Override
 	public void commandRequest(RequestBodyPost requestBodyPost, String fileNameParam, String userAddress) throws Exception {
		onlyOfficeRepository.commandRequest(requestBodyPost, fileNameParam, userAddress);
	}
	
	@Override
	public FileModel getConfig(String namaFile, OnlyofficeUser onlyofficeUser) throws Exception {
		return onlyOfficeRepository.getConfig(namaFile, onlyofficeUser);
	}

}
