package org.Sikoling.main.restful.perusahaan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.perusahaan.IPerusahaanService;
import org.Sikoling.main.restful.person.PersonDTO;
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
@Path("perusahaan")
public class PerusahaanController {
	
	@Inject
	private IPerusahaanService perusahaanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PerusahaanDTO save(PerusahaanDTO d, @Context SecurityContext securityContext) {
//		Principal principal = securityContext.getUserPrincipal();
//		String userName = principal.getName();
		PersonDTO creator = new PersonDTO();
		creator.setNik(securityContext.getUserPrincipal().getName());
		
//		return new PerusahaanDTO(perusahaanService.save(d.toPerusahaan(), creator.toPerson()));
		return new PerusahaanDTO(perusahaanService.save(d.toPerusahaan()));
	}
	
	@Path("{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PerusahaanDTO update(@PathParam("id") String id, PerusahaanDTO d) {
		return new PerusahaanDTO(perusahaanService.updateById(id, d.toPerusahaan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(perusahaanService.delete(id));
	}
	
	@Path("is_eksis")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	public Boolean cekPerusahaan(@QueryParam("id") String id) {
		return perusahaanService.cekById(id);
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getAll() {
		return perusahaanService.getAll()
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("id")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PerusahaanDTO getByNpwp(@QueryParam("id") String id) {
		return new PerusahaanDTO(perusahaanService.getByNpwp(id));
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return perusahaanService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByNama(@QueryParam("nama") String nama) {
		return perusahaanService.getByNama(nama)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return perusahaanService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("person/{personId}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	public List<PerusahaanDTO> getByIdPerson(@PathParam("personId") String personId) {
		return perusahaanService.getByIdPerson(personId)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
}
