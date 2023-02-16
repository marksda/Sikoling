package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.Sikoling.main.restful.permohonan.RegisterPermohonanDTO;
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
//		Jsonb jsonb = JsonbBuilder.create();
		
		JsonParser jsonParser = Json.createParser(entityStream);
		Event event;
		String key = "";		
		while (jsonParser.hasNext()) {	
			 event = jsonParser.next();
		     if (event == JsonParser.Event.KEY_NAME ) {			    	 
		         key = jsonParser.getString();
		     }
	     }
		
		return registerPermohonanDTO;
	}

}
