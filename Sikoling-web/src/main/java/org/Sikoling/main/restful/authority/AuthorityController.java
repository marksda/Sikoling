package org.Sikoling.main.restful.authority;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.authority.IAuthorityService;
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
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("authority")
public class AuthorityController {
	
	@Inject
	private IAuthorityService authorityService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public AuthorityDTO save(AuthorityDTO d) {
		return new AuthorityDTO(authorityService.save(d.toAuthority()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public AuthorityDTO update(AuthorityDTO d) {
		return new AuthorityDTO(authorityService.update(d.toAuthority()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(authorityService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<AuthorityDTO> getAll() {
		return authorityService.getAll()
				.stream()
				.map(t -> new AuthorityDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<AuthorityDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return authorityService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new AuthorityDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<AuthorityDTO> getByNama(@QueryParam("nama") String nama) {
		return authorityService.getByNama(nama)
				.stream()
				.map(t -> new AuthorityDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<AuthorityDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return authorityService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new AuthorityDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("user_name}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	public AuthorityDTO getByIdPerson(@QueryParam("userName") String userName) {
		return new AuthorityDTO(authorityService.getByUserName(userName));
	}
	
}
