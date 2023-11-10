package org.Sikoling.main.restful.storages.onlyoffice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredOnlyofficeAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.apache.commons.io.FilenameUtils;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Stateless
@LocalBean
@Path("onlyoffice")
public class OnlyofficeController {
	
	@Inject
	private IOnlyofficeService onlyofficeService;
	
	@Inject
	private IOtoritasService authorityService;
	
	@Inject
	private ILocalStorageService localStorageService;
		
	@Path("config")
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@JsonbProperty(nillable=true)
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public FileModel getConfigEditor(@Context SecurityContext securityContext, @QueryParam("fileNameParam") String fileNameParam) throws Exception {
		Otoritas pengurusPermohonan = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		Person person = pengurusPermohonan.getPerson();
		List<String> userDescription = new ArrayList<String>();
		OnlyofficeUser onlyofficeUser = null;
		String idHakAkses = pengurusPermohonan.getHakAkses().getId();
		String namaHakAkses = pengurusPermohonan.getHakAkses().getNama();
		String mode = "view";
		
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
			mode="edit";
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

		return onlyofficeService.getConfig(fileNameParam, mode, null, null, onlyofficeUser);
	}
	
	@Path("download")
	@GET
	@RequiredOnlyofficeAuthorization
	public Response getDownload(@QueryParam("fileNameParam") String fileNameParam) throws IOException {
		try {
			File tempFile = localStorageService.download(fileNameParam);	
			String fileName = FilenameUtils.getName(fileNameParam);
			String fileType = Files.probeContentType(tempFile.toPath());
		    String fileSize = String.valueOf(tempFile.length());
		    return Response.status( Response.Status.OK )
			        .header("Content-Length", fileSize)
			        .header("Content-Type", fileType)
			        .header("Content-Disposition", "attachment; filename*=UTF-8\'\'" + fileName)
			        .entity(tempFile)
			        .build();  
		} catch (IOException e) {
			throw new IOException("file tidak ada");
		}		 
	}
	
	@Path("resources")
	@GET
	@RequiredOnlyofficeAuthorization
	public Response getResources(@QueryParam("fileNameParam") String fileNameParam) throws IOException {
//		InputStream inputStream = null;
//		String fileType = null;
//		String fileSize = null;
//		String fileName = null;
		try {
			File tempFile = localStorageService.download(fileNameParam);	
			String fileName = FilenameUtils.getName(fileNameParam);
//			File tempFile = File.createTempFile(FilenameUtils.getBaseName(fileName), "." + FilenameUtils.getExtension(fileName));
//			FileOutputStream out = new FileOutputStream(tempFile);
//			IOUtils.copy(inputStream, out);	
			String fileType = Files.probeContentType(tempFile.toPath());
			String fileSize = String.valueOf(tempFile.length());
//		    tempFile.delete();
			return Response.status( Response.Status.OK )
			        .header("Content-Length", fileSize)
			        .header("Content-Type", fileType)
			        .header("Content-Disposition", "attachment; filename*=UTF-8\'\'" + fileName)
			        .entity(tempFile)
			        .build();   
		} catch (IOException e) {
			throw new IOException("file tidak ada");
		}
		
		
	}

	@Path("track")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredOnlyofficeAuthorization
	public JsonObject getResponseTracker(@QueryParam("fileNameParam") String fileNameParam, 
			@QueryParam("userAddress") String userAddress, JsonObject d) throws IOException {
		int error = 0;
		try {
			onlyofficeService.commandRequest(d, fileNameParam, userAddress);
		} catch (Exception e) {
			error = 1;
		}
		return Json.createObjectBuilder().add("error", error).build();
	}
		
}
