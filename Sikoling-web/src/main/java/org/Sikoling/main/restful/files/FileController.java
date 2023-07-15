package org.Sikoling.main.restful.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
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
		String subPathLocation = "";
		String urlLocatorFeedBack = "";
		
		switch (subPath) {
			case "personal_identification": 
				subPathLocation = File.separator.concat("personal_identification")
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
							fileKey = UUID.randomUUID().toString() + "-" + fileDetail.getFileName();
							storageService.save(fileKey, uploadedInputStream, subPathLocation);
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
				subPathLocation = File.separator.concat("other")
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
							fileKey = UUID.randomUUID().toString() + "-" + fileDetail.getFileName();
							storageService.save(fileKey, uploadedInputStream, subPathLocation);
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
	
	@Path("sec/dok/{idRegPerusahaan}/{npwp}/{idRegDokumen}")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public ImageDTO uploadFileSecure(
			@PathParam("idRegPerusahaan") String idRegPerusahaan,
			@PathParam("npwp") String npwp,
			@PathParam("idRegDokumen") String idRegDokumen,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
		
		String subPathLocation = File.separator.concat("dok")
				        		.concat(File.separator)
				        		.concat(npwp);
		String fileKey = UUID.randomUUID().toString() + "-" + idRegDokumen.concat("-").concat(fileDetail.getFileName());
		storageService.save(fileKey, uploadedInputStream, subPathLocation);
		String urlLocatorFeedBack = "sec/dok/"
				.concat(npwp)
				.concat("/")
				.concat(fileKey);
		
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("id", idRegDokumen));
		QueryParamFilters queryParamFilters = new QueryParamFilters(0, 0, filters, null);
		
		RegisterDokumen registerDokumen = registerDokumenService.getDaftarData(queryParamFilters).get(0);
		
		RegisterDokumen registerDokumenBaru = new RegisterDokumen(
				idRegDokumen, registerDokumen.getDokumen(), registerDokumen.getPerusahaan(), 
				fileKey, registerDokumen.getStatusDokumen(), registerDokumen.getTanggalRegistrasi(), 
				registerDokumen.getUploader(), registerDokumen.getStatusVerified());
		
		registerDokumenService.update(registerDokumenBaru);
        
        return new ImageDTO(uriInfo.getBaseUri() + urlLocatorFeedBack, fileKey);
	}
	
	
	
}