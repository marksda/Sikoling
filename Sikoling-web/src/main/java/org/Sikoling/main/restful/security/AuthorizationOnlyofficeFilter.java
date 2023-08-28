package org.Sikoling.main.restful.security;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.Sikoling.ejb.abstraction.service.security.IOnlyofficeTokenManagerService;

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
@RequiredOnlyofficeAuthorization
public class AuthorizationOnlyofficeFilter implements ContainerRequestFilter {

	@Inject
	private IOnlyofficeTokenManagerService onlyofficeTokenManagerService;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = Optional.ofNullable(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new NotAuthorizedException("Authorization header not found"));
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        Map<String, Object> claimSet = onlyofficeTokenManagerService.readToken(token);
        
        if(claimSet == null) {
        	throw new NotAuthorizedException("Authorization error");
        }
        
	}
	
}
