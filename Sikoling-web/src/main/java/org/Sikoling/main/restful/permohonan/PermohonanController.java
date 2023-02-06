package org.Sikoling.main.restful.permohonan;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;
import org.Sikoling.ejb.abstraction.service.authority.IAuthorityService;
import org.Sikoling.ejb.abstraction.service.permohonan.IRegisterPermohonanService;
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
@Path("register_permohonan")
public class PermohonanController {
	@Inject
	private IAuthorityService authorityService;
	
	@Inject 
	private IRegisterPermohonanService registerPermohonanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPermohonanDTO save(RegisterPermohonanDTO d, @Context SecurityContext securityContext) {
		Authority kreator = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		d.setPengurusPermohonan(new AuthorityDTO(kreator));
		d.setStatusWali(new StatusWaliDTO(new StatusWali("01", null)));
		d.setStatusTahapPemberkasan(new PosisiTahapPemberkasanDTO(new PosisiTahapPemberkasan("0", null, null)));
		LocalDate tanggalRegistrasi = LocalDate.now();
		d.setTanggalRegistrasi(tanggalRegistrasi);
		
		return new RegisterPermohonanDTO(
				registerPermohonanService.save(d.toRegisterPermohonan())
				);
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPermohonanDTO update(RegisterPermohonanDTO d) {		
		return new RegisterPermohonanDTO(registerPermohonanService.update(d.toRegisterPermohonan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(registerPermohonanService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPermohonanDTO> getAll() {
		return registerPermohonanService.getAll()
				.stream()
				.map(t -> new RegisterPermohonanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPermohonanDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerPermohonanService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new RegisterPermohonanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("user/{idUser}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPermohonanDTO> getByIdUser(@PathParam("idUser") String idUser) {
		return registerPermohonanService.getByIdPengakses(idUser)
				.stream()
				.map(t -> new RegisterPermohonanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("perusahaan/{idRegister}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPermohonanDTO> getByIdPerusahaan(@PathParam("idRegister") String idRegister) {
		return registerPermohonanService.getByIdPengakses(idRegister)
				.stream()
				.map(t -> new RegisterPermohonanDTO(t))
				.collect(Collectors.toList());
	}

}
