package org.Sikoling.main.restful.perusahaan;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.service.authority.IAuthorityService;
import org.Sikoling.ejb.abstraction.service.perusahaan.IRegisterPerusahaanService;
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
@Path("register_perusahaan")
public class PerusahaanController {
	
	@Inject
	private IRegisterPerusahaanService registerPerusahaanService;
	
	@Inject
	private IAuthorityService authorityService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO save(PerusahaanDTO d, @Context SecurityContext securityContext) {		
		Authority authority = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		RegisterPerusahaanDTO registerPerusahaanDTO = new RegisterPerusahaanDTO();
		registerPerusahaanDTO.setTanggalRegistrasi(LocalDate.now());
		PersonDTO kreator = new PersonDTO();
		kreator.setNik(authority.getPerson().getNik());
		registerPerusahaanDTO.setKreator(kreator);
		registerPerusahaanDTO.setPerusahaan(d);
		
		return new RegisterPerusahaanDTO(registerPerusahaanService.save(registerPerusahaanDTO.toRegisterPerusahaan()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO update(RegisterPerusahaanDTO d) {
		return new RegisterPerusahaanDTO(registerPerusahaanService.update(d.toRegisterPerusahaan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(registerPerusahaanService.delete(id));
	}
	
	@Path("is_eksis")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Boolean cekPerusahaan(@QueryParam("id") String id) {
		return registerPerusahaanService.cekById(id);
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getAll() {
		return registerPerusahaanService.getAll()
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("id")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO getByNpwp(@QueryParam("id") String id) {
		return new RegisterPerusahaanDTO(registerPerusahaanService.getByNpwp(id));
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerPerusahaanService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getByNama(@QueryParam("nama") String nama) {
		return registerPerusahaanService.getByNama(nama)
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerPerusahaanService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("person/{personId}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getByIdPerson(@PathParam("personId") String personId) {
		return registerPerusahaanService.getByIdPerson(personId)
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
}
