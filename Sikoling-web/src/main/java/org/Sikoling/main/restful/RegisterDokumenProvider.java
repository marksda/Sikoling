package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.Sikoling.main.restful.dokumen.DokumenDTO;
import org.Sikoling.main.restful.dokumen.NibOssDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.perusahaan.PerusahaanDTO;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
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
		
		RegisterDokumenDTO registerDokumenDTO = new RegisterDokumenDTO();
		Jsonb jsonb = JsonbBuilder.create();
		
		JsonParser jsonParser = Json.createParser(entityStream);
		while (jsonParser.hasNext()) {
		     Event event = jsonParser.next();
		     if (event == JsonParser.Event.KEY_NAME ) {	
		    	 
		         String key = jsonParser.getString();
		         event = jsonParser.next();
				 JsonObject jsonObject = jsonParser.getObject();
				 
		         if (key.equals("dokumen")) {
	     			 DokumenDTO d = null;
					 String id = jsonObject.getString("id");
					 switch (id) {
					 case "010301":
						d = jsonb.fromJson(jsonObject.toString(), NibOssDTO.class);
						registerDokumenDTO.setDokumen(d); 
						break;
					 default:
						d = null;
						break;
					}
		         }
		         else if(key.equals("perusahaan")) {
		        	 PerusahaanDTO perusahaanDTO = jsonb.fromJson(jsonObject.toString(), PerusahaanDTO.class);
		        	 registerDokumenDTO.setPerusahaan(perusahaanDTO);
		         }
		         
		     }
		     
		 }		
		
		return registerDokumenDTO;
	}

}
