package org.Sikoling.main.restful.kategoripelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kategoripelakuusaha.IKategoriPelakuUsahaServices;
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
@Path("kategori_pelaku_usaha")
public class KategoriPelakuUsaha {

	@Inject
	private IKategoriPelakuUsahaServices kategoriPelakuUsahaService;	

	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public KategoriPelakuUsahaDTO saveKategori(KategoriPelakuUsahaDTO d) {
        return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.save(d.toKategoriPelakuUsaha()));
    }

	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriPelakuUsahaDTO updateKategori(KategoriPelakuUsahaDTO d) {
		return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.update(d.toKategoriPelakuUsaha()));
	}
	
	@Path("id/{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriPelakuUsahaDTO updateKategoriById(@PathParam("id") String id, KategoriPelakuUsahaDTO d) {
		return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.updateById(id, d.toKategoriPelakuUsaha()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(kategoriPelakuUsahaService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<KategoriPelakuUsahaDTO> getDaftarKategoriPelakuUsaha(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return kategoriPelakuUsahaService.getDaftarKategoriPelakuUsaha(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new KategoriPelakuUsahaDTO(t))
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
		
		return kategoriPelakuUsahaService.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
