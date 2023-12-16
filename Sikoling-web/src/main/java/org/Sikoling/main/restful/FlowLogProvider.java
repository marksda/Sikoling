package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.Sikoling.main.restful.log.FlowLogDTO;
import org.Sikoling.main.restful.log.FlowLogPermohonanDTO;
import org.Sikoling.main.restful.log.KategoriFlowLogDTO;

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
public class FlowLogProvider implements MessageBodyReader<FlowLogDTO> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == FlowLogDTO.class;
	}

	@Override
	public FlowLogDTO readFrom(Class<FlowLogDTO> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		FlowLogDTO flowLogDTO;
		
		try {			
			Jsonb jsonb = JsonbBuilder.create();
			JsonObject d = jsonb.fromJson(entityStream, JsonObject.class);
			KategoriFlowLogDTO kategoriFlowLogDTO = jsonb.fromJson(d.getJsonObject("kategoriFlowLog").toString(), KategoriFlowLogDTO.class);
			
			switch (kategoriFlowLogDTO.getId()) {
			case "1":
				flowLogDTO = jsonb.fromJson(d.toString(), FlowLogPermohonanDTO.class);
				break;
			case "2":
				flowLogDTO = null;
				break;
			default:
				flowLogDTO = null;
				break;
			}
			
			return flowLogDTO;
		}
		catch (Exception e) {
			return null;
		}
	}

}
