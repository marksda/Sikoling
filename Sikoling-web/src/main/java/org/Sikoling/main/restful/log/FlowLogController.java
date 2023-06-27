package org.Sikoling.main.restful.log;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.service.authority.IAutorityService;
import org.Sikoling.ejb.abstraction.service.log.IFlowLogService;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
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
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("flow_log")
public class FlowLogController {

	@Inject
	private IFlowLogService flowLogService;
	
	@Inject
	private IAutorityService authorityService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FlowLogDTO save(FlowLogDTO d, @Context SecurityContext securityContext) throws IOException {
		Otoritas pengakses = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		d.setPengakses(new OtoritasDTO(pengakses));		
		
		return new FlowLogDTO(flowLogService.save(d.toFlowLog()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FlowLogDTO update(FlowLogDTO d) {
		return new FlowLogDTO(flowLogService.update(d.toFlowLog()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FlowLogDTO updateId(@PathParam("idLama") String idLama, FlowLogDTO d) throws IOException {
		return new FlowLogDTO(flowLogService.updateId(idLama, d.toFlowLog()));
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FlowLogDTO delete(FlowLogDTO d) throws IOException {
		return new FlowLogDTO(flowLogService.delete(d.toFlowLog()));
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<FlowLogDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return flowLogService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> {
					if(t.getKategoriFlowLog().getId().equals("1")) {
						FlowLogPermohonanDTO flowLogPermohonanDTO = new FlowLogPermohonanDTO((FlowLogPermohonan) t);
						return flowLogPermohonanDTO;
					}
					else {
						return new FlowLogDTO(t);
					}					
				})
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
		
		return flowLogService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
}
