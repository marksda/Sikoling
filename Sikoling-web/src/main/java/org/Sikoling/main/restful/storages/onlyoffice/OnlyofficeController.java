package org.Sikoling.main.restful.storages.onlyoffice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.CommentGroups;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.FileModel;
import org.Sikoling.ejb.abstraction.entity.onlyoffice.OnlyofficeUser;
import org.Sikoling.ejb.abstraction.service.onlyoffice.IOnlyofficeService;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
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
import jakarta.ws.rs.core.SecurityContext;

@Stateless
@LocalBean
@Path("onlyoffice")
public class OnlyofficeController {
	
	@Inject
	private IOnlyofficeService onlyofficeService;
	
	@Inject
	private IOtoritasService authorityService;

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
	public FileModel getConfigEditor(@Context SecurityContext securityContext, @QueryParam("fileNameParam") String fileNameParam) throws Exception {		
		Otoritas pengurusPermohonan = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		Person person = pengurusPermohonan.getPerson();
		List<String> userDescription = new ArrayList<String>();
		OnlyofficeUser onlyofficeUser = null;
		String idHakAkses = pengurusPermohonan.getHakAkses().getId();
		String namaHakAkses = pengurusPermohonan.getHakAkses().getNama();
		
		switch (idHakAkses) {
		case "01":
			userDescription.add("Belongs to group " + namaHakAkses);
			userDescription.add("Can review all the changes");
			userDescription.add("Can perform all actions with comments");
			userDescription.add("The file favorite state is undefined");
			userDescription.add("Can create files from templates using data from the editor");
			userDescription.add("Can see the information about all users");
			onlyofficeUser = new OnlyofficeUser(
					person.getNik(), person.getNama(), person.getKontak().getEmail(), namaHakAkses, 
					Arrays.asList(pengurusPermohonan.getHakAkses().getNama()), new CommentGroups(), null, new ArrayList<String>(), 
					userDescription, null, null
					);
			break;
		case "09":
			userDescription.add("Belongs to group " + namaHakAkses);
			userDescription.add("Can review changes made by group " + namaHakAkses + " users");
			userDescription.add("Can view, edit and remove comments left by Group" + namaHakAkses + "users");
			userDescription.add("The file favorite state is undefined");
			userDescription.add("Can't create files from templates using data from the editor");
			userDescription.add("Can see the information about all users");
			onlyofficeUser = new OnlyofficeUser(
					person.getNik(), person.getNama(), person.getKontak().getEmail(), namaHakAkses, 
					Arrays.asList(namaHakAkses), 
					new CommentGroups(Arrays.asList(namaHakAkses), Arrays.asList(namaHakAkses), Arrays.asList(namaHakAkses)), 
					null, Arrays.asList("create"), 
					userDescription, null, null
					);
			break;
		default:
			throw new Exception("Group error");
		}	
				
		return onlyofficeService.getConfig(fileNameParam, onlyofficeUser);
	}
	
	@Path("download/{subPath}/{jenisPath}")
	@GET
    @Produces({MediaType.APPLICATION_JSON})
//	@RequiredAuthorization
//	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FileModel getDownload(@PathParam("subPath") String subPath, @PathParam("jenisPath") String jenisPath, @QueryParam("namaFile") String namaFile) throws IOException {		
		return null;
	}
	
	@Path("create/{subPath}/{jenisPath}")
	@POST
    @Produces({MediaType.APPLICATION_JSON})
//	@RequiredAuthorization
//	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FileModel setCreate(@PathParam("subPath") String subPath, @PathParam("jenisPath") String jenisPath, @QueryParam("namaFile") String namaFile) throws IOException {		
		return null;
	}
	
}
