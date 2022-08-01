package org.Sikoling.main.restful;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class NotAuthorizedExceptionHandler implements ExceptionMapper<NotAuthorizedException> {
	
	private final Logger logger = Logger.getLogger(NotAuthorizedExceptionHandler.class.getName());

	@Override
	public Response toResponse(NotAuthorizedException exception) {
		logger.log(Level.SEVERE, exception.getMessage(), exception);
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));		
		
		return Response.status(401)
				.entity(new ExceptionResponse(exception.getMessage(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
