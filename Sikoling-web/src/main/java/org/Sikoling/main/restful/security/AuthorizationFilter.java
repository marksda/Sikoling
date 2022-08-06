package org.Sikoling.main.restful.security;

import java.io.IOException;
import java.util.Optional;

import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;


@Provider
@Stateless
@LocalBean
@RequiredAuthorization
public class AuthorizationFilter implements ContainerRequestFilter {
	
	@Inject
	private ITokenValidationService tokenValidationService;	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = Optional.ofNullable(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new NotAuthorizedException("Authorization header not found"));
 
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        tokenValidationService.validate(token);
	}

}
