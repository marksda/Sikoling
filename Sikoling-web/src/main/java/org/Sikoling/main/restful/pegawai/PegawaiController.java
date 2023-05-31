package org.Sikoling.main.restful.pegawai;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.pegawai.IPegawaiPerusahaanService;
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
@Path("pegawai_perusahaan")
public class PegawaiController {
	
	@Inject
	private IPegawaiPerusahaanService pegawaiPerusahaanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiPerusahaanDTO save(PegawaiPerusahaanDTO d) {		
		return new PegawaiPerusahaanDTO(pegawaiPerusahaanService.save(d.toPegawaiPerusahaan()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PegawaiPerusahaanDTO update(PegawaiPerusahaanDTO d) {
		return new PegawaiPerusahaanDTO(pegawaiPerusahaanService.update(d.toPegawaiPerusahaan()));
	}
	
	@Path("id/{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PegawaiPerusahaanDTO updateById(@PathParam("id") String id, PegawaiPerusahaanDTO d) {
		return new PegawaiPerusahaanDTO(pegawaiPerusahaanService.updateById(id, d.toPegawaiPerusahaan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(pegawaiPerusahaanService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<PegawaiPerusahaanDTO> getDaftarPegawaiPerusahaan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return pegawaiPerusahaanService.getDaftarPegawai(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PegawaiPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getCountDaftarPegawaiPerusahaan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return pegawaiPerusahaanService.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
}
