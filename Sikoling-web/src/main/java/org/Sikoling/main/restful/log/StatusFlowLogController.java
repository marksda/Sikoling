package org.Sikoling.main.restful.log;

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
    public StatusFlowLogDTO save(StatusFlowLogDTO d) {
        return new StatusFlowLogDTO(statusFlowLogServices.save(d.toStatusFlowLog()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public StatusFlowLogDTO update(StatusFlowLogDTO d) {		
		return new StatusFlowLogDTO(statusFlowLogServices.update(d.toStatusFlowLog()));
	}
	
	@Path("id/{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public StatusFlowLogDTO updateById(@PathParam("id") String id, StatusFlowLogDTO d) {
		return new StatusFlowLogDTO(statusFlowLogServices.updateById(id, d.toStatusFlowLog()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<StatusFlowLogDTO> getDaftarStatusFlowLog(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return statusFlowLogServices.getDaftarStatusFlowLog(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new StatusFlowLogDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getCountDaftarStatusFlowLog(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return statusFlowLogServices.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	

}
