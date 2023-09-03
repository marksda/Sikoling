package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

import org.Sikoling.main.restful.dokumen.AktaPendirianDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.dokumen.StatusDokumenDTO;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;

@Provider
@Consumes("application/json")
public class RegisterDokumenProvider implements MessageBodyReader<RegisterDokumenDTO> {	
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == RegisterDokumenDTO.class;
	}

	@Override
	public RegisterDokumenDTO readFrom(Class<RegisterDokumenDTO> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		RegisterDokumenDTO registerDokumenDTO;
		
		try {
			Jsonb jsonb = JsonbBuilder.create();
			JsonObject d = jsonb.fromJson(entityStream, JsonObject.class);
			JsonObject dokumen = d.getJsonObject("dokumen");
			String idDokumen = dokumen.getString("id");
			registerDokumenDTO = new RegisterDokumenDTO();
			
			switch (idDokumen) {
			case "010101":
					registerDokumenDTO.setDokumen(jsonb.fromJson(d.getJsonObject("dokumen").toString(), AktaPendirianDTO.class));
				break;
			default:
				return null;
			}
			
			registerDokumenDTO.setId(d.getString("id", null));
			registerDokumenDTO.setRegisterPerusahaan(jsonb.fromJson(d.getJsonObject("registerPerusahaan").toString(), RegisterPerusahaanDTO.class));
			registerDokumenDTO.setLokasiFile(d.getString("lokasiFile", null));
			
			try {
				registerDokumenDTO.setStatusDokumen(jsonb.fromJson(d.getJsonObject("statusDokumen").toString(), StatusDokumenDTO.class));
			} catch (Exception e) {
				registerDokumenDTO.setStatusDokumen(null);
			}
			
			
			String tanggalRegistrasi = d.getString("tanggalRegistrasi", null);
			if(tanggalRegistrasi != null) {
				registerDokumenDTO.setTanggalRegistrasi(LocalDate.parse(tanggalRegistrasi));
			}	
			
			try {
				registerDokumenDTO.setUploader(jsonb.fromJson(d.getJsonObject("uploader").toString(), OtoritasDTO.class));
			} catch (Exception e) {
				registerDokumenDTO.setUploader(null);
			}
			
			try {
				registerDokumenDTO.setUploader(jsonb.fromJson(d.getJsonObject("uploader").toString(), OtoritasDTO.class));
				registerDokumenDTO.setStatusVerified(d.getBoolean("statusVerified"));
			} catch (Exception e) {
				registerDokumenDTO.setStatusVerified(Boolean.FALSE);
			}
			
			return registerDokumenDTO;
		} catch (Exception e) {
			return null;
		}	
		
//		RegisterDokumenDTO registerDokumenDTO = new RegisterDokumenDTO();
//		Jsonb jsonb = JsonbBuilder.create();
//		
//		JsonParser jsonParser = Json.createParser(entityStream);
//		
//		while (jsonParser.hasNext()) {
//		     Event event = jsonParser.next();
//		     if (event == JsonParser.Event.KEY_NAME ) {			    	 
//		         String key = jsonParser.getString();
//		         event = jsonParser.next();
//				 JsonObject jsonObject = jsonParser.getObject();
//				 
//		         if (key.equals("dokumen")) {
//	     			 DokumenDTO d = null;
//					 String id = jsonObject.getString("id");
//					 switch (id) {
//					 case "010301":
//						d = jsonb.fromJson(jsonObject.toString(), DokumenNibOssDTO.class);
//						registerDokumenDTO.setDokumen(d); 
//						break;
//					 case "010101":
//							d = jsonb.fromJson(jsonObject.toString(), DokumenNibOssDTO.class);
//							registerDokumenDTO.setDokumen(d); 
//							break;
//					 default:
//						d = null;
//						break;
//					}
//		         }
//		         else if(key.equals("perusahaan")) {
//		        	 RegisterPerusahaanDTO perusahaanDTO = jsonb.fromJson(jsonObject.toString(), RegisterPerusahaanDTO.class);
//		        	 registerDokumenDTO.setRegisterPerusahaan(perusahaanDTO);
//		         }
//		         
//		     }
//		     
//		 }		
//		
//		return registerDokumenDTO;
	}

}
