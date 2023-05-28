package org.Sikoling.main.restful.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.pelakuusaha.IPelakuUsahaServices;
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
@Path("pelaku_usaha")
public class PelakuUsahaController {
		
	@Inject
	private IPelakuUsahaServices pelakuUsahaServices;
		
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PelakuUsahaDTO save(PelakuUsahaDTO d) {		
        return new PelakuUsahaDTO(pelakuUsahaServices.save(d.toPelakuUsaha()));
    }
		
	@Path("{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PelakuUsahaDTO update(@PathParam("id") String id, PelakuUsahaDTO d) {		
		return new PelakuUsahaDTO(pelakuUsahaServices.updateById(id, d.toPelakuUsaha()));
	}
		
//	@GET
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<PelakuUsahaDTO> getAll() {
//        return pelakuUsahaServices.getALL()
//                .stream()
//                .map(t -> new PelakuUsahaDTO(t))
//                .collect(Collectors.toList());
//    }

	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<PelakuUsahaDTO> getDaftarPelakuUsaha(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return pelakuUsahaServices.getDaftarPelakuUsaha(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PelakuUsahaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getCountDaftarKategoriPelakuUsaha(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return pelakuUsahaServices.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
