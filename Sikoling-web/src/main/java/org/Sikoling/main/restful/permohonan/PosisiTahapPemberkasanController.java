package org.Sikoling.main.restful.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.permohonan.IPosisiTahapPemberkasanService;
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
@Path("posisi_tahap_pemberkasan")
public class PosisiTahapPemberkasanController {
	
	@Inject
	private IPosisiTahapPemberkasanService posisiTahapPemberkasanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public PosisiTahapPemberkasanDTO save(PosisiTahapPemberkasanDTO d) {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.save(d.toPosisiTahapPemberkasan()));
		
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public PosisiTahapPemberkasanDTO update(PosisiTahapPemberkasanDTO d) {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.update(d.toPosisiTahapPemberkasan()));		
	}
	
	@Path("id/{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PosisiTahapPemberkasanDTO updateById(@PathParam("id") String id, PosisiTahapPemberkasanDTO d) {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.updateById(id, d.toPosisiTahapPemberkasan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(posisiTahapPemberkasanService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<PosisiTahapPemberkasanDTO> getDaftarPosisiTahapPemberkasan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return posisiTahapPemberkasanService.getDaftarPosisiTahapPemberkasan(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PosisiTahapPemberkasanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getCountDaftarPosisiTahapPemberkasan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return posisiTahapPemberkasanService.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
