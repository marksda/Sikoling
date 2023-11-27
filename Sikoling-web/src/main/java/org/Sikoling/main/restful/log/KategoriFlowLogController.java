package org.Sikoling.main.restful.log;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.log.IKategoriLogService;
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
@Path("kategori_log")
public class KategoriFlowLogController {
	@Inject
	private IKategoriLogService kategoriLogService;
		
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
    public KategoriFlowLogDTO save(KategoriFlowLogDTO d) throws IOException {
		return new KategoriFlowLogDTO(kategoriLogService.save(d.toKategoriFlowLog()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public KategoriFlowLogDTO update(KategoriFlowLogDTO d) {		
		return new KategoriFlowLogDTO(kategoriLogService.update(d.toKategoriFlowLog()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public KategoriFlowLogDTO updateId(@PathParam("idLama") String idLama, KategoriFlowLogDTO d) throws IOException {
		return new KategoriFlowLogDTO(kategoriLogService.updateId(idLama, d.toKategoriFlowLog()));
	}
	
	@Path("{idKategoriFlowLog}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public KategoriFlowLogDTO delete(@PathParam("idKategoriFlowLog") String idKategoriFlowLog) throws IOException {
		KategoriFlowLogDTO d = new KategoriFlowLogDTO();
		d.setId(idKategoriFlowLog);
		
		return new KategoriFlowLogDTO(kategoriLogService.delete(d.toKategoriFlowLog()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public List<KategoriFlowLogDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return kategoriLogService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new KategoriFlowLogDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public Long getJumlahData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return kategoriLogService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
