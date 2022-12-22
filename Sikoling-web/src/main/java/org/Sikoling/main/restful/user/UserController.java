package org.Sikoling.main.restful.user;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.user.IUserService;
import org.Sikoling.main.restful.message.SimpleResponseDTO;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("user")
public class UserController {
	
	@Inject
	private IUserService userService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public UserDTO save(UserDTO u) {
		return new UserDTO(userService.save(u.toUser()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public UserDTO update(UserDTO u) {
		return new UserDTO(userService.update(u.toUser()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<UserDTO> getAll() {
		return userService.getAll()
				.stream()
				.map(u -> new UserDTO(u))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<UserDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return userService.getAllByPage(page, pageSize)
				.stream()
				.map(u -> new UserDTO(u))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<UserDTO> getByQueryNama(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return userService.getByQueryNama(nama)
				.stream()
				.map(u -> new UserDTO(u))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<UserDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return userService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(u -> new UserDTO(u))
				.collect(Collectors.toList());
	}

	@Path("cek_user_name")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
	public Boolean cekUserName(@QueryParam("userName") String nama) {		
		return nama.length() > 0? userService.cekUserName(nama) : false;
	}
	
	@Path("get_token")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public ResponTokenDTO getToken(CredentialDTO u) {
		return new ResponTokenDTO(userService.getToken(u.toCredential()));
	}
	
	@Path("refresh_token")
	@POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON})
	public ResponTokenDTO getRefreshToken(String refreshToken) {
		return new ResponTokenDTO(userService.refreshToken(refreshToken));
	}
	
	@Path("registrasi")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public SimpleResponseDTO setRegistrasi(RegistrasiDTO r) {
		return new SimpleResponseDTO(userService.addRegistrasi(r.getCredential().toCredential(), r.getPerson().toPerson()));
	}
	
}
