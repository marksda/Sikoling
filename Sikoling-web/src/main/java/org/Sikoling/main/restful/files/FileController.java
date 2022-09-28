package org.Sikoling.main.restful.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.Sikoling.ejb.abstraction.service.file.IStorageService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("files")
public class FileController {	
	
	@Context
	private UriInfo uriInfo;	
	
	@Inject
	private IStorageService storageService;	
	
	//uploading file with no security
	@Path("nosec/{subpath}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO uploadFileNoSecurity(
			@PathParam("subPath") String subPath,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		String fileKey;
		String pathLocation;
		switch (subPath) {
			case "personal_identification": 
				fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream, "personal_identification");
				pathLocation = "file"
		        		.concat(File.separator)
		        		.concat("nosec")
		        		.concat(File.separator)
		        		.concat(subPath)
		        		.concat(File.separator)
		        		.concat(fileKey);
				break;
			default:
				fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream, "other");
				pathLocation = "file"
		        		.concat(File.separator)
		        		.concat("nosec")
		        		.concat(File.separator)
		        		.concat("other")
		        		.concat(File.separator)
		        		.concat(fileKey);
		}
		
        
        
        return new ImageDTO(uriInfo.getBaseUri() + pathLocation, fileKey);
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiredAuthorization
	public ImageDTO uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		String fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream, "other");
        
        return new ImageDTO(uriInfo.getBaseUri() + "files/other/" + fileKey, fileKey);
	}
	
	@Path("nosec/{subPath}/{fileKey}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public InputStream loadFile(@PathParam("subPath") String subPath, @PathParam("fileKey") String fileKey) throws IOException {
        return storageService.load(subPath, fileKey);
    }
	
}