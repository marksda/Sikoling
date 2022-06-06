package org.Sikoling.main.restful;

import org.Sikoling.ejb.abstraction.repository.IPermohonanRepository;
import org.Sikoling.ejb.abstraction.service.permohonan.ServiceBasicPermohonan;
import org.Sikoling.ejb.abstraction.service.permohonan.IServicePermohonan;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;

@Stateless
@LocalBean
public class RestfulProvider {
	
	@Produces
	public IServicePermohonan getLayananPermohonan(
			@Infrastructure IPermohonanRepository permohonanRepository) {
		return new ServiceBasicPermohonan(permohonanRepository);
	}
	
}
