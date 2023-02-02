package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.Sikoling.main.restful.permohonan.RegisterPermohonanDTO;
import jakarta.json.Json;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;


public class RegisterPermohonanProvider implements MessageBodyReader<RegisterPermohonanDTO> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == RegisterPermohonanDTO.class;
	}

	@Override
	public RegisterPermohonanDTO readFrom(Class<RegisterPermohonanDTO> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		RegisterPermohonanDTO registerPermohonanDTO = new RegisterPermohonanDTO();
		Jsonb jsonb = JsonbBuilder.create();
		
		JsonParser jsonParser = Json.createParser(entityStream);
		while (jsonParser.hasNext()) {
		     Event event = jsonParser.next();
		     if (event == JsonParser.Event.KEY_NAME ) {	
		    	 
		         String key = jsonParser.getString();
		         event = jsonParser.next();
//				 JsonObject jsonObject = jsonParser.getObject();
				 
		         if (key.equals("kategoriPermohonan")) {
	     			registerPermohonanDTO = jsonb.fromJson(entityStream, RegisterPermohonanDTO.class);
		         }
		         else {
		        	 registerPermohonanDTO = jsonb.fromJson(entityStream, RegisterPermohonanDTO.class);
		         }	
		     }
	     }
		
		return registerPermohonanDTO;
	}

}
