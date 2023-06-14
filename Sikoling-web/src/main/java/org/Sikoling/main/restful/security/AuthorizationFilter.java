package org.Sikoling.main.restful.security;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.service.authority.IAutorityService;
import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;


@Provider
@Stateless
@LocalBean
@RequiredAuthorization
public class AuthorizationFilter implements ContainerRequestFilter {
	
	@Context
    private ResourceInfo resourceInfo;
	
	@Inject
	private ITokenValidationService tokenValidationService;		
	
	@Inject
	private IAutorityService authorityService;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		
		String authorizationHeader = Optional.ofNullable(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new NotAuthorizedException("Authorization header not found"));		
 
        String token = authorizationHeader.substring("Bearer".length()).trim();
    	Map<String, Object> claims = tokenValidationService.validate(token);
    	
    	Class<?> resourceClass = resourceInfo.getResourceClass();
        List<Role> classRoles = extractRoles(resourceClass);
        
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(resourceMethod);
        
        try {
        	if (methodRoles.isEmpty()) {
                checkPermissions(classRoles, claims);
            } else {
                checkPermissions(methodRoles, claims);
            }
		} catch (Exception e) {
			throw new NotAuthorizedException(e.toString());
		}
                        
        requestContext.setSecurityContext(new SecurityContext() {
			
			@Override
			public boolean isUserInRole(String role) {						
				return true;
			}
			
			@Override
			public boolean isSecure() {
				return currentSecurityContext.isSecure();
			}
			
			@Override
			public Principal getUserPrincipal() {
				return () -> claims.get("email").toString();
			}
			
			@Override
			public String getAuthenticationScheme() {
				return null;
			}
		});
        
	}
	
	// Extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            RequiredRole requiredRole = annotatedElement.getAnnotation(RequiredRole.class);
            if (requiredRole == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = requiredRole.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }
    
    private void checkPermissions(List<Role> allowedRoles, Map<String, Object> claims) throws Exception {
    	Autority authority = authorityService.getByUserName(claims.get("email").toString());		
    	boolean grandPermission = false;
    	String hakAkses = "ADMIN";
    	if(authority.getHakAkses().getId().equals("09")) {
    		hakAkses = "PEMRAKARSA";
    	}
    	
    	Iterator<Role> iterator = allowedRoles.iterator();    	
    	while(iterator.hasNext()) {    		
    		Role role = iterator.next();
    		if(hakAkses.equalsIgnoreCase(role.toString())) {
    			grandPermission = true;
    			break;
    		}    		
    	}
    	
    	if(!grandPermission) {
    		throw new Exception("Role not found");
    	}
    	        
    }

}
