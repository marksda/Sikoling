package org.Sikoling.main.restful.otoritas;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.keycloackuser.IKeyCloackUserService;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.Sikoling.main.restful.user.CredentialDTO;
import org.Sikoling.main.restful.user.UserDTO;
import org.glassfish.jersey.media.multipart.FormDataParam;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("otoritas")
public class OtoritasController {
	
	@Inject
	private IOtoritasService otoritasService;
	
	@Inject
	private IKeyCloackUserService userService;
	
	@POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public OtoritasDTO save(@FormDataParam("credentialData") String credentialData, @FormDataParam("otoritasData") String otoritasData) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		CredentialDTO credentialDTO = jsonb.fromJson(credentialData, CredentialDTO.class);
		OtoritasDTO otoritasDTO = jsonb.fromJson(otoritasData, OtoritasDTO.class);		
		otoritasDTO = new OtoritasDTO(otoritasService.save(otoritasDTO.toOtoritas()));
		
		UserDTO userDTO = new UserDTO();
		userDTO.setCredential(credentialDTO);
		userDTO.setPerson(otoritasDTO.getPerson());
		
		userService.save(userDTO.toUser());
		
		return otoritasDTO;
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public OtoritasDTO update(OtoritasDTO d) {
		return new OtoritasDTO(otoritasService.update(d.toOtoritas()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public OtoritasDTO updateId(@PathParam("idLama") String idLama, OtoritasDTO d) throws IOException {
		return new OtoritasDTO(otoritasService.updateId(idLama, d.toOtoritas()));
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public OtoritasDTO delete(OtoritasDTO d) throws IOException {
		return new OtoritasDTO(otoritasService.delete(d.toOtoritas()));
	}
		
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<OtoritasDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return otoritasService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new OtoritasDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getJumlahData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return otoritasService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
