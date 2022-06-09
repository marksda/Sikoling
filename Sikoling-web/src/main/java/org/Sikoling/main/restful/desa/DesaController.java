package org.Sikoling.main.restful.desa;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.service.desa.IDesaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class DesaController {
	
	@Inject
	private IDesaService desaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DesaDTO save(DesaDTO desaDTO) {
		return new DesaDTO(desaService.save(new Desa(desaDTO.getId(), desaDTO.getNama(), desaDTO.getKecamatan().toKecamatan())));
	}

}
