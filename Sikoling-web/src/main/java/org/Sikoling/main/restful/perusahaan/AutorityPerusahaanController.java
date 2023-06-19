package org.Sikoling.main.restful.perusahaan;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.perusahaan.IAutorityPerusahaanService;
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
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("autority_perusahaan")
public class AutorityPerusahaanController {
	
	@Inject
	private IAutorityPerusahaanService autorityPerusahaanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
    public AutorityPerusahaanDTO save(AutorityPerusahaanDTO d) throws IOException {
        return new AutorityPerusahaanDTO(autorityPerusahaanService.save(d.toAutorityPerusahaan()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public AutorityPerusahaanDTO update(AutorityPerusahaanDTO d) {		
		return new AutorityPerusahaanDTO(autorityPerusahaanService.update(d.toAutorityPerusahaan()));
	}
	
	@Path("id/{idLamaAutority}/{idLamaRegisterPerusahaan}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public AutorityPerusahaanDTO updateId(@PathParam("idLamaAutority") String idLamaAutority,
			@PathParam("idLamaRegisterPerusahaan") String idLamaRegisterPerusahaan, AutorityPerusahaanDTO d) throws IOException {
		return new AutorityPerusahaanDTO(autorityPerusahaanService.updateId(idLamaAutority, idLamaRegisterPerusahaan, d.toAutorityPerusahaan()));
	}
	
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public AutorityPerusahaanDTO delete(AutorityPerusahaanDTO d) throws IOException {
		return new AutorityPerusahaanDTO(autorityPerusahaanService.delete(d.toAutorityPerusahaan()));
	}

	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<AutorityPerusahaanDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return autorityPerusahaanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new AutorityPerusahaanDTO(t))
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
		
		return autorityPerusahaanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
