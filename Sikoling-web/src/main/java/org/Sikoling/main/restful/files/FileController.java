package org.Sikoling.main.restful.files;

import java.io.IOException;
import java.io.InputStream;

import org.Sikoling.ejb.abstraction.service.file.IStorageService;
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
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		String fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream);
        
        return new ImageDTO(uriInfo.getBaseUri() + "files/" + fileKey);
	}
	
	@Path("{fileKey}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public InputStream loadFile(@PathParam("fileKey") String fileKey) throws IOException {
        return storageService.load(fileKey);
    }
}
