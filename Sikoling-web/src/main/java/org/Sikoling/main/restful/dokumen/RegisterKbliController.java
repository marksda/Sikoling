package org.Sikoling.main.restful.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterKbliService;
import org.Sikoling.main.restful.response.DeleteResponseDTO;

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
@Path("register_kbli")
public class RegisterKbliController {
	
	@Inject
	private IRegisterKbliService registerKbliService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public RegisterKbliDTO save(RegisterKbliDTO t) {
		return new RegisterKbliDTO(registerKbliService.save(t.toRegisterKbli()));
	}
	
	@Path("id/{nib}/{kode}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public RegisterKbliDTO update(@PathParam("nib") String nib, @PathParam("kode") String kode, RegisterKbliDTO t) {
		return new RegisterKbliDTO(registerKbliService.update(t.toRegisterKbli()));
	}
	
	@Path("id/{nib}/{kode}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("nib") String nib, @PathParam("kode") String kode) {
		return new DeleteResponseDTO(registerKbliService.delete(nib, kode));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getAll() {
		return registerKbliService.getAll()
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerKbliService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByNama(@QueryParam("nama") String nama) {
		return registerKbliService.getByNama(nama)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerKbliService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("kode")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByKode(@QueryParam("kode") String kode) {
		return registerKbliService.getByKode(kode)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("kode/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByKodeAndPage(@QueryParam("kode") String kode,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerKbliService.getByKodeAndPage(kode, page, pageSize)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nib")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByNib(@QueryParam("nib") String nib) {
		return registerKbliService.getByNib(nib)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nib/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterKbliDTO> getByNibAndPage(@QueryParam("nib") String nib,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerKbliService.getByNibAndPage(nib, page, pageSize)
				.stream()
				.map(t -> new RegisterKbliDTO(t))
				.collect(Collectors.toList());
	}

}
