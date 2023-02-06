package org.Sikoling.main.restful.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.permohonan.IStatusWaliService;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("status_wali")
public class StatusWaliController {
	
	@Inject
	private IStatusWaliService statusWaliService;
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<StatusWaliDTO> getAll() {
		return statusWaliService.getAll()
				.stream()
				.map(t -> new StatusWaliDTO(t))
				.collect(Collectors.toList());
	}

}
