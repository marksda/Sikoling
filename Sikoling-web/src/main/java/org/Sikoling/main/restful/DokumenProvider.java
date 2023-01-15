package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.Sikoling.main.restful.dokumen.DokumenDTO;
import org.Sikoling.main.restful.dokumen.NibOssDTO;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;

@Provider
@Consumes("application/json")
public class DokumenProvider implements MessageBodyReader<DokumenDTO> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return DokumenDTO.class == type;
	}

	@Override
	public DokumenDTO readFrom(Class<DokumenDTO> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		JsonParser jsonParser = Json.createParser(entityStream);
		JsonObject jsonObject = jsonParser.getObject();
		String id = jsonObject.getString("id");
		
		Jsonb jsonb = JsonbBuilder.create();
		
		DokumenDTO d;
		
		switch (id) {
		case "010301":
			d = jsonb.fromJson(entityStream, NibOssDTO.class);
			break;
		default:
			d = null;
			break;
		}
		
		
		return d;
	}

}
