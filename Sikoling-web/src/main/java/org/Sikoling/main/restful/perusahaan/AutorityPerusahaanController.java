package org.Sikoling.main.restful.perusahaan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.perusahaan.IAutorityPerusahaanService;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.response.DeleteResponseDTO;
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
    public AutorityPerusahaanDTO save(AutorityPerusahaanDTO d) {
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
	public AutorityPerusahaanDTO updateById(@PathParam("idLamaAutority") String idLamaAutority,
			@PathParam("idLamaRegisterPerusahaan") String idLamaRegisterPerusahaan, AutorityPerusahaanDTO d) {
		return new AutorityPerusahaanDTO(autorityPerusahaanService.updateById(idLamaAutority, idLamaRegisterPerusahaan, d.toAutorityPerusahaan()));
	}
	
	@Path("{idAutority}/{idRegisterPerusahaan}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("idAutority") String idAutority, @PathParam("idRegisterPerusahaan") String idRegisterPerusahaan) {
		return new DeleteResponseDTO(autorityPerusahaanService.delete(idAutority, idRegisterPerusahaan));
	}

	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<AutorityPerusahaanDTO> getDaftarAutorityPerusahaan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return autorityPerusahaanService.getDaftarAutorityPerusahaan(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new AutorityPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getCountDaftarAutorityPerusahaan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return autorityPerusahaanService.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
