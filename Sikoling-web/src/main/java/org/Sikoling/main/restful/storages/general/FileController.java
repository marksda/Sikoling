package org.Sikoling.main.restful.storages.general;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
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
@Path("file")
public class FileController {
	
	@Inject
	private ILocalStorageService localStorageService;
	
	@Inject
	private IOtoritasService authorityService;
	
	@Path("upload")
	@POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public JsonObject setUploadFile(@QueryParam("fileNameParam") String fileNameParam, @Context SecurityContext securityContext, 
			@FormDataParam("file") InputStream inputStream, @FormDataParam("file") FormDataContentDisposition contentDisposition) throws IOException {		
		Otoritas pengurusPermohonan = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		try {
			String fileKey = UUID.randomUUID().toString() + "-" + pengurusPermohonan.getPerson().getNik()+ "-" + FilenameUtils.getName(fileNameParam);
			localStorageService.upload(fileKey, inputStream, FilenameUtils.getFullPathNoEndSeparator(fileNameParam));
			return Json.createObjectBuilder().add("uri", FilenameUtils.getFullPath(fileNameParam).concat(fileKey)).build();
		} catch (Exception e) {
			throw new IOException("Upload error");
		}
	}
	
	@Path("create")
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public JsonObject setCreate(@QueryParam("fileNameParam") String fileNameParam, 
			@QueryParam("sample") String sample, @Context SecurityContext securityContext) throws IOException {		
		Otoritas pengurusPermohonan = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		try {
			String fileName = FilenameUtils.getName(fileNameParam);
			String fileKey = FilenameUtils.getBaseName(fileName) + "-" + pengurusPermohonan.getPerson().getNik() + "." + FilenameUtils.getExtension(fileNameParam);
			localStorageService.create(fileKey, FilenameUtils.getFullPathNoEndSeparator(fileNameParam));
			return Json.createObjectBuilder().add("uri", FilenameUtils.getFullPath(fileNameParam).concat(fileKey)).build();
		} catch (Exception e) {
			throw new IOException("Create file error");
		}
	}
	
	@Path("download")
	@GET
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Response getDownload(@QueryParam("fileNameParam") String fileNameParam) throws IOException {
		InputStream inputStream = null;
		String fileType = null;
		String fileSize = null;
		String fileName = null;
		try {
			inputStream = localStorageService.download(fileNameParam);	
			fileName = FilenameUtils.getName(fileNameParam);
			File tempFile = File.createTempFile(FilenameUtils.getBaseName(fileName), "." + FilenameUtils.getExtension(fileName));
			FileOutputStream out = new FileOutputStream(tempFile);
			IOUtils.copy(inputStream, out);	
		    fileType = Files.probeContentType(tempFile.toPath());
		    fileSize = String.valueOf(tempFile.length());
		    tempFile.delete();
		} catch (IOException e) {
		    e.printStackTrace();
		}	
		
		return Response.status( Response.Status.OK )
		        .header("Content-Length", fileSize)
		        .header("Content-Type", fileType)
		        .header("Content-Disposition", "attachment; filename*=UTF-8\'\'" + fileName)
		        .entity(inputStream)
		        .build();   
	}

}
