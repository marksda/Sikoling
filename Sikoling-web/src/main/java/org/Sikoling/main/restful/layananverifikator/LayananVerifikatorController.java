package org.Sikoling.main.restful.layananverifikator;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@LocalBean
@Path("layananverifikator")
public class LayananVerifikatorController {

	@Path("login")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response login(UserDTO userDTO) {
		return null;
	}
	
}
