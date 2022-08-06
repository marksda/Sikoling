package org.Sikoling.main.restful.security;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.Sikoling.ejb.abstraction.service.security.AuthorizationException;
import org.Sikoling.main.restful.ExceptionResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class AuthorizationExceptionHandler implements ExceptionMapper<AuthorizationException> {
	
	private final Logger logger = Logger.getLogger(AuthorizationExceptionHandler.class.getName());

	@Override
	public Response toResponse(AuthorizationException e) {
		logger.log(Level.WARNING, e.getMessage(), e);

        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        return Response.status(401)
                .entity(new ExceptionResponse(e.getMessage(), stringWriter.toString()))
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
