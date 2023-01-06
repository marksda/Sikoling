package org.Sikoling.main.restful.log;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.service.authority.IAuthorityService;
import org.Sikoling.ejb.abstraction.service.log.IFlowLogService;
import org.Sikoling.main.restful.authority.AuthorityDTO;
import org.Sikoling.main.restful.response.DeleteResponseDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Stateless
@LocalBean
@Path("flow_log")
public class FlowLogController {

	@Inject
	private IFlowLogService flowLogService;
	
	@Inject
	private IAuthorityService authorityService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public FlowLogDTO save(FlowLogDTO d, @Context SecurityContext securityContext) {
		Authority pengakses = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		d.setPengakses(new AuthorityDTO(pengakses));		
		
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
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(flowLogService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<FlowLogDTO> getAll() {
		return flowLogService.getAll()
				.stream()
				.map(t -> new FlowLogDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<FlowLogDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return flowLogService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new FlowLogDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("user/{idUser}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<FlowLogDTO> getByIdUser(@PathParam("idUser") String idUser) {
		return flowLogService.getByIdPengakses(idUser)
				.stream()
				.map(t -> {
					if(t instanceof FlowLogPermohonan) {
						return new FlowLogPermohonanDTO((FlowLogPermohonan) t);
					}
					else {
						return new FlowLogDTO(t);
					}
				})
				.collect(Collectors.toList());
	}
	
	@Path("kategori/{idKategori}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<FlowLogDTO> getByIdKategori(@PathParam("idKategori") String idKategori) {
		return flowLogService.getByIdKategori(idKategori)
				.stream()
				.map(t -> new FlowLogDTO(t))
				.collect(Collectors.toList());
	}
	
}
