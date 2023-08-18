package org.Sikoling.main.restful.onlyoffice;

import java.io.IOException;

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
	public JsonObject getResponseTracker(RequestBodyPostDTO u) throws IOException {
		JsonObject jsonObject = Json.createObjectBuilder().add("error", 0).build();
		return jsonObject;
	}
	
}
