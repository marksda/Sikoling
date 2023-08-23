package org.Sikoling.main.restful.storages.onlyoffice;

import java.io.IOException;

import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.service.onlyoffice.IOnlyofficeService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("onlyoffice")
public class OnlyofficeController {
	
	@Inject IOnlyofficeService onlyofficeService;

	@Path("track")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public JsonObject getResponseTracker(RequestBodyPostDTO d, @Context HttpHeaders httpHeaders) throws IOException {
		int error = 0;
		try {
			onlyofficeService.commandRequest(null);
		} catch (Exception e) {
			error = 1;
		}
		return Json.createObjectBuilder().add("error", error).build();
	}
	
	@Path("config")
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FileModel getConfigEditor(@QueryParam("fileNameParam") String fileNameParam) throws IOException {		
		return null;
	}
	
	@Path("download/{subPath}/{jenisPath}")
	@GET
    @Produces({MediaType.APPLICATION_JSON})
//	@RequiredAuthorization
//	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FileModel getDownload(@PathParam("subPath") String subPath, @PathParam("jenisPath") String jenisPath, @QueryParam("namaFile") String namaFile) throws IOException {		
		return null;
	}
	
}
