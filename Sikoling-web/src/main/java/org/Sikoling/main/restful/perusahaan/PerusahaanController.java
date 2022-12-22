package org.Sikoling.main.restful.perusahaan;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.service.authority.IAuthorityService;
import org.Sikoling.ejb.abstraction.service.perusahaan.IRegisterPerusahaanService;
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
		Authority kreator = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		
		return new RegisterPerusahaanDTO(
				registerPerusahaanService.save(
						new RegisterPerusahaan(
								null, 
								LocalDate.now(), 
								kreator, 
								null, 
								d.toPerusahaan()
								)
						)
				);
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPerusahaanDTO update(PerusahaanDTO d) {
		RegisterPerusahaanDTO registerPerusahaanDTO = new RegisterPerusahaanDTO();
		registerPerusahaanDTO.setPerusahaan(d);
		
		return new RegisterPerusahaanDTO(registerPerusahaanService.update(registerPerusahaanDTO.toRegisterPerusahaan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(registerPerusahaanService.delete(id));
	}
	
	@Path("kepemilikan/{idPerusahaan}/{idPerson}")
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO deleteLinkKepemilikanPerusahaan(@PathParam("idPerusahaan") String idPerusahaan,
			@PathParam("idPerson") String idPerson) {
		return new DeleteResponseDTO(
				registerPerusahaanService.deleteLinkKepemilikanPerusahaan(idPerson, idPerusahaan)
				);
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
	
	@Path("kreator/{idKreator}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getByIdPerson(@PathParam("idKreator") String idKreator) {
		return registerPerusahaanService.getByIdKreator(idKreator)
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("kepemilikan/{idLinkKepemilikan}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPerusahaanDTO> getByIdLinkKepemilikan(@PathParam("idLinkKepemilikan") String idLinkKepemilikan) {
		return registerPerusahaanService.getByIdLinkKepemilikan(idLinkKepemilikan)
				.stream()
				.map(t -> new RegisterPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
}
