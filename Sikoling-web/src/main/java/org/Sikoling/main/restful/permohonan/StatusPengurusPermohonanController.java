package org.Sikoling.main.restful.permohonan;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.permohonan.IStatusPengurusPermohonanService;
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
@Path("status_pengurus_permohonan")
public class StatusPengurusPermohonanController {
	
	@Inject
	private IStatusPengurusPermohonanService statusPengurusPermohonanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
    public StatusPengurusPermohonanDTO save(StatusPengurusPermohonanDTO d) throws IOException {
        return new StatusPengurusPermohonanDTO(statusPengurusPermohonanService.save(d.toStatusPengurusPermohonan()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public StatusPengurusPermohonanDTO update(StatusPengurusPermohonanDTO d) {		
		return new StatusPengurusPermohonanDTO(statusPengurusPermohonanService.update(d.toStatusPengurusPermohonan()));
	}
	
	@Path("{idStatusPengurusPermohonan}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public StatusPengurusPermohonanDTO delete(@PathParam("idStatusPengurusPermohonan") String idStatusPengurusPermohonan) throws IOException {
		StatusPengurusPermohonanDTO d = new StatusPengurusPermohonanDTO();
		d.setId(idStatusPengurusPermohonan);
		
		return new StatusPengurusPermohonanDTO(statusPengurusPermohonanService.delete(d.toStatusPengurusPermohonan()));
	}

	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public StatusPengurusPermohonanDTO updateById(@PathParam("idLama") String idLama, StatusPengurusPermohonanDTO d) throws IOException {
		return new StatusPengurusPermohonanDTO(statusPengurusPermohonanService.updateId(idLama, d.toStatusPengurusPermohonan()));
	}	

	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public List<StatusPengurusPermohonanDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return statusPengurusPermohonanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new StatusPengurusPermohonanDTO(t))
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
		
		return statusPengurusPermohonanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
		
}
