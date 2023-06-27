package org.Sikoling.main.restful.perusahaan;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
import org.Sikoling.ejb.abstraction.service.perusahaan.IRegisterPerusahaanService;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
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
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("register_perusahaan")
public class RegisterPerusahaanController {
	
	@Inject
	private IRegisterPerusahaanService registerPerusahaanService;
	
	@Inject
	private IOtoritasService otoritasService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO save(PerusahaanDTO d, @Context SecurityContext securityContext) throws IOException {		
		Otoritas kreator = otoritasService.getByUserName(securityContext.getUserPrincipal().getName());
		
		return new RegisterPerusahaanDTO(
				registerPerusahaanService.save(
						new RegisterPerusahaan(
								null, 
								LocalDate.now(), 
								kreator, 
								null, 
								d.toPerusahaan(),
								false
								)
						)
				);
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO update(PerusahaanDTO d) {
		RegisterPerusahaanDTO registerPerusahaanDTO = new RegisterPerusahaanDTO();
		registerPerusahaanDTO.setPerusahaan(d);
		
		return new RegisterPerusahaanDTO(registerPerusahaanService.update(registerPerusahaanDTO.toRegisterPerusahaan()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO updateId(@PathParam("idLama") String idLama, RegisterPerusahaanDTO d) throws IOException {
		return new RegisterPerusahaanDTO(registerPerusahaanService.updateId(idLama, d.toRegisterPerusahaan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO delete(RegisterPerusahaanDTO d) throws IOException {
		return new RegisterPerusahaanDTO(registerPerusahaanService.delete(d.toRegisterPerusahaan()));
	}
		
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return registerPerusahaanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
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
		
		return registerPerusahaanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
