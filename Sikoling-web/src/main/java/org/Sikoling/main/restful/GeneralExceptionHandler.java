package org.Sikoling.main.restful;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class GeneralExceptionHandler implements ExceptionMapper<Exception> {
	
	private final Logger logger = Logger.getLogger(GeneralExceptionHandler.class.getName());

	@Override
	public Response toResponse(Exception e) {
		logger.log(Level.SEVERE, e.getMessage(), e);

        StringWriter stringWriter = new StringWriter();

        e.printStackTrace(new PrintWriter(stringWriter));

        return Response.status(500)
                .entity(new ExceptionResponse(e.getMessage(), stringWriter.toString()))
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
