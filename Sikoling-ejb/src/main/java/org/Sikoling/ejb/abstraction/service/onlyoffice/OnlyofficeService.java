package org.Sikoling.ejb.abstraction.service.onlyoffice;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.RequestBodyPost;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;

public class OnlyofficeService implements IOnlyofficeService {
	
	private final IOnlyOfficeRepository onlyOfficeRepository;

	public OnlyofficeService(IOnlyOfficeRepository onlyOfficeRepository) {
		this.onlyOfficeRepository = onlyOfficeRepository;
	}

	@Override
 	public void commandRequest(RequestBodyPost requestBodyPost) throws Exception {
		onlyOfficeRepository.commandRequest(requestBodyPost);
	}

}
