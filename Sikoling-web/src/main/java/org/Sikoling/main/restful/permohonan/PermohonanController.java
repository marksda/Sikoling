package org.Sikoling.main.restful.permohonan;

import org.Sikoling.ejb.abstraction.service.permohonan.ServicePermohonan;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class PermohonanController {
	
	@Inject
	private ServicePermohonan layananPermohonan;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public PermohonanDTO save(PermohonanDTO permohonan) {
		return new PermohonanDTO(layananPermohonan.save(permohonan.toPermohonan()));
	}
	
}
