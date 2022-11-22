package org.Sikoling.main.restful.dokumen;

import org.Sikoling.ejb.abstraction.service.dokumen.IKbliService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("kbli")
public class KbliController {
	
	@Inject
	private IKbliService kbliService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KbliDTO save(KbliDTO t) {
		return new KbliDTO(kbliService.save(t.toKbli()));
	}

}
