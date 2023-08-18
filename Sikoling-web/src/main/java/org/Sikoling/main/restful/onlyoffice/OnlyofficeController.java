package org.Sikoling.main.restful.onlyoffice;

import java.io.IOException;

import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("onlyoffice")
public class OnlyofficeController {

	@Path("track")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public JsonObject getResponseTracker(RequestBodyPostDTO d) throws IOException {
		int valid = 0;
		switch (d.getStatus()) {
		case 0:
			
			break;
		default:
			valid = 0;
			break;
		}
		return Json.createObjectBuilder().add("error", valid).build();
	}
	
}
