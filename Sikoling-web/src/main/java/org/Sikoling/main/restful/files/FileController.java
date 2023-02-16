package org.Sikoling.main.restful.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterDokumenService;
import org.Sikoling.ejb.abstraction.service.file.IStorageService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
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
	
	@Inject
	private IRegisterDokumenService registerDokumenService;
	
//	@Inject
//	private IRegisterPerusahaanService registerPerusahaanService;
	
	//uploading file with no security
	@Path("nosec/{subPath}/{id}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO uploadFileNoSecurity(
			@PathParam("subPath") String subPath, 
			@PathParam("id") String id,
			FormDataMultiPart formParams ) throws IOException {
		Map<String, List<FormDataBodyPart>> fields = formParams.getFields();
		String fileKey = "";
		String pathLocation = "";
		String urlLocatorFeedBack = "";
		
		switch (subPath) {
			case "personal_identification": 
				pathLocation = "personal_identification"
        		.concat(File.separator)
        		.concat(id);
				
				Iterator<Map.Entry<String, List<FormDataBodyPart>>> itrPersonalIdentification = fields.entrySet().iterator();
				
				while (itrPersonalIdentification.hasNext()) {
					Map.Entry<String, List<FormDataBodyPart>> entry = itrPersonalIdentification.next();
					List<FormDataBodyPart> formDataBodyParts = entry.getValue();
					for(FormDataBodyPart i : formDataBodyParts) {
						FormDataContentDisposition fileDetail = i.getFormDataContentDisposition();
						InputStream uploadedInputStream = i.getEntityAs(InputStream.class);
						try {
							fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream, pathLocation);
							urlLocatorFeedBack = "files/personal_identification/"
									.concat(id)
									.concat("/")
									.concat(fileKey);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				break;
			default:
				pathLocation = "other"
        		.concat(File.separator)
        		.concat(id);
				
				Iterator<Map.Entry<String, List<FormDataBodyPart>>> itrOther = fields.entrySet().iterator();
				
				while (itrOther.hasNext()) {
					Map.Entry<String, List<FormDataBodyPart>> entry = itrOther.next();
					List<FormDataBodyPart> formDataBodyParts = entry.getValue();
					for(FormDataBodyPart i : formDataBodyParts) {
						FormDataContentDisposition fileDetail = i.getFormDataContentDisposition();
						InputStream uploadedInputStream = i.getEntityAs(InputStream.class);
						try {
							fileKey = storageService.save(fileDetail.getFileName(), uploadedInputStream, pathLocation);
							urlLocatorFeedBack = "files/other/"
									.concat(id)
									.concat("/")
									.concat(fileKey);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		}			
			
//		return new ImageDTO(uriInfo.getBaseUri() + pathLocation.replaceAll("\\", "/"), fileKey);
		return new ImageDTO(uriInfo.getBaseUri() + urlLocatorFeedBack, fileKey);
	}
	
	@Path("nosec/{subPath}/{fileKey}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public InputStream loadFile(@PathParam("subPath") String subPath, @PathParam("fileKey") String fileKey) throws IOException {
        return storageService.load(subPath, fileKey);
    }
	
	@Path("nosecure/dok/{npwp}/{fileKey}")
    @GET
    @Produces("application/pdf")
    public InputStream loadFileNoSecure(@PathParam("npwp") String npwp, @PathParam("fileKey") String fileKey) throws IOException {
		String subPath = "dok"
        		.concat(File.separator)
        		.concat(npwp);
		return storageService.load(subPath, fileKey);
    }
	
	@Path("sec/dok/{npwp}/{fileKey}")
    @GET
    @RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
    public InputStream loadFileSecure(@PathParam("npwp") String npwp, @PathParam("fileKey") String fileKey) throws IOException {
		String subPath = "dok"
        		.concat(File.separator)
        		.concat(npwp);
		return storageService.load(subPath, fileKey);
    }
	
	@Path("sec/dok/{npwp}/{id}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public ImageDTO uploadFileSecure(
			@PathParam("npwp") String npwp,
			@PathParam("id") String id,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		
		String pathLocation = "dok"
        		.concat(File.separator)
        		.concat(npwp);
		String fileKey = storageService.save(id.concat("-").concat(fileDetail.getFileName()), uploadedInputStream, pathLocation);
		String urlLocatorFeedBack = "sec/dok/"
				.concat(npwp)
				.concat("/")
				.concat(fileKey);
		
		RegisterDokumen registerDokumen = registerDokumenService.getByIdRegisterDokumen(id);
		
		RegisterDokumen registerDokumenBaru = new RegisterDokumen(
				id, registerDokumen.getDokumen(), registerDokumen.getPerusahaan(), 
				fileKey, registerDokumen.getStatusDokumen(), registerDokumen.getTanggalRegistrasi(), 
				registerDokumen.getUploader(), registerDokumen.getStatusVerified());
		
		registerDokumenService.update(registerDokumenBaru);
        
        return new ImageDTO(uriInfo.getBaseUri() + urlLocatorFeedBack, fileKey);
	}
	
	
	
}