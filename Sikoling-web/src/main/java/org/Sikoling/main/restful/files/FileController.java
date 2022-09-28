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
	@Path("nosec/{subPath}")
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
	
//	@Path("pegawai")
//    @PUT
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response putPegawai(FormDataMultiPart formParams) {
//        validateToken.getValidate(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
//        String hasil;
//        try {
//            List<FormDataBodyPart> partNip = formParams.getFields("nip");
//            String nip = partNip.get(0).getEntityAs(String.class);
//            List<FormDataBodyPart> partNama = formParams.getFields("nama");
//            String nama = partNama.get(0).getEntityAs(String.class);
//            List<FormDataBodyPart> partTelepon = formParams.getFields("no_handphone");
//            String telepon = partTelepon.get(0).getEntityAs(String.class);
//            List<FormDataBodyPart> partAlamat = formParams.getFields("alamat");
//            String alamat = partAlamat.get(0).getEntityAs(String.class); 
//            List<FormDataBodyPart> partEmail = formParams.getFields("email");
//            String email = partEmail.get(0).getEntityAs(String.class);    
//            List<FormDataBodyPart> partStatus = formParams.getFields("status");
//            Boolean status = partStatus.get(0).getEntityAs(Boolean.class);
//            String data = "{\"nip\":\"" + nip +"\", \"nama\":\"" + 
//                        nama +"\", \"no_handphone\":\"" + telepon + "\", \"alamat\":\"" +
//                        alamat + "\", \"email\":\"" + email + "\", \"status\":" + status.toString() + "}";       
//            hasil = masterSessionBean.setPegawai(data);
//            if(hasil.equalsIgnoreCase("gagal")) {
//                    hasil = "{\"status\": 401, \"keterangan\": \"gagal\"}";
//            }
//            else {
//                List<FormDataBodyPart> parts = formParams.getFields("file");
//                if(parts != null) {
//                    for (FormDataBodyPart part : parts) {
//                        FormDataContentDisposition fileDetail = part.getFormDataContentDisposition();
//                        String namaFile = URLDecoder.decode(fileDetail.getFileName(), StandardCharsets.UTF_8.name());
//                        InputStream uploadedInputStream = part.getEntityAs(InputStream.class);
//                        masterSessionBean.setFotoPegawai(nip, namaFile, uploadedInputStream);
//                    }                
//                }
//                hasil = "{\"status\": 200, \"keterangan\": \"sukses\"}";                            
//            }
//            return hasilResponse.responSukses(hasil);
//        } catch (UnsupportedEncodingException e) {
//            hasil = "{\"status\": 401, \"keterangan\": \"error parsing value type\"}";
//            return hasilResponse.responNotFound(hasil);
//        }        
//    }    
	
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