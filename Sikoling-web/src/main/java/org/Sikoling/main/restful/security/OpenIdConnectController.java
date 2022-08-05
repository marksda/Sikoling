package org.Sikoling.main.restful.security;

import org.Sikoling.ejb.abstraction.service.security.IOpenIdConnectService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("openidconnect")
public class OpenIdConnectController {
	
	@Inject
	private IOpenIdConnectService openIdConnectService;
	
	@POST
	@Path("token")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public TokenDTO save(@FormParam("grant_type") String grantType, @FormParam("code") String code, @FormParam("redirect_uri") String redirectUrl) {
		return new TokenDTO(openIdConnectService.requestToken(grantType, code, redirectUrl));
	}
}
