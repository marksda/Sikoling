package org.Sikoling.main.restful.log;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.log.IStatusFlowLogServices;
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
@Path("status_flow_log")
public class StatusFlowLogController {
	
	@Inject
	private IStatusFlowLogServices statusFlowLogServices;

	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
    public StatusFlowLogDTO save(StatusFlowLogDTO d) throws IOException {
		return new StatusFlowLogDTO(statusFlowLogServices.save(d.toStatusFlowLog()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public StatusFlowLogDTO update(StatusFlowLogDTO d) {		
		return new StatusFlowLogDTO(statusFlowLogServices.update(d.toStatusFlowLog()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public StatusFlowLogDTO updateId(@PathParam("idLama") String idLama, StatusFlowLogDTO d) throws IOException {
		return new StatusFlowLogDTO(statusFlowLogServices.updateId(idLama, d.toStatusFlowLog()));
	}
	
	@Path("{idStatusFlowLog}")
	@DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public StatusFlowLogDTO delete(@PathParam("idStatusFlowLog") String idStatusFlowLog) throws IOException {
		StatusFlowLogDTO d = new StatusFlowLogDTO();
		d.setId(idStatusFlowLog);
		
		return new StatusFlowLogDTO(statusFlowLogServices.delete(d.toStatusFlowLog()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public List<StatusFlowLogDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return statusFlowLogServices.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new StatusFlowLogDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public Long getJumlahData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return statusFlowLogServices.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
