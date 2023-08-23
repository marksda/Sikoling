package org.Sikoling.main.restful.storages.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Stateless
@LocalBean
@Path("file")
public class FileController {
	
	@Inject
	private ILocalStorageService localStorageService;
	
	@Inject
	private IOtoritasService authorityService;
	
	@Path("upload/{subPath}/{jenisPath}")
	@POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public JsonObject setUpload(@Context SecurityContext securityContext, @FormDataParam("file") File file, @PathParam("subPath") String subPath, @PathParam("jenisPath") String jenisPath,
			@FormDataParam("file") FormDataContentDisposition contentDisposition) throws IOException {		
		Otoritas pengurusPermohonan = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		try {
			String fileKey = UUID.randomUUID().toString().concat("-").concat(pengurusPermohonan.getPerson().getNik()).concat("-").concat(contentDisposition.getFileName());
			InputStream uploadedInputStream = new FileInputStream(file);
			String subPathLocation = File.separator.concat("subPath").concat(File.separator).concat(jenisPath);
			localStorageService.upload(fileKey, uploadedInputStream, subPathLocation);
			return Json.createObjectBuilder().add("uri", subPathLocation.concat("?fileNameParam=").concat(fileKey)).build();
		} catch (Exception e) {
			throw new IOException("Upload error");
		}
	}

}
