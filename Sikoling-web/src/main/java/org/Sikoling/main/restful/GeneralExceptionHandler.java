package org.Sikoling.main.restful;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {
	
//	private final Logger logger = Logger.getLogger(GeneralExceptionHandler.class.getName());

	@Override
	public Response toResponse(Exception e) {
//		logger.log(Level.SEVERE, e.getMessage(), e);
//
//        StringWriter stringWriter = new StringWriter();
//
//        e.printStackTrace(new PrintWriter(stringWriter));

        return Response.status(500)
//                .entity(new ExceptionResponse(e.getMessage(), stringWriter.toString()))
        		.entity(new ExceptionResponse(e.getMessage(), "none"))
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
