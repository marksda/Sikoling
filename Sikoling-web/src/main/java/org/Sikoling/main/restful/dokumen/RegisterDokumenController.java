package org.Sikoling.main.restful.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterDokumenService;
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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("register_dokumen")
public class RegisterDokumenController {
	
	@Inject
	private IRegisterDokumenService registerDokumenService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public RegisterDokumenDTO save(RegisterDokumenDTO d) {		
		return new RegisterDokumenDTO(registerDokumenService.save(d.toRegisterDokumen()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public RegisterDokumenDTO update(@QueryParam("id") String id, RegisterDokumenDTO d) {
		return new RegisterDokumenDTO(registerDokumenService.updateById(id, d.toRegisterDokumen()));
	}

	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@QueryParam("id") String id) {
		return new DeleteResponseDTO(registerDokumenService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getAll() {
		return registerDokumenService.getAll()
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerDokumenService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("perusahaan/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByNamaPerusahaan(@QueryParam("namaPerusahaan") String nama) {
		return registerDokumenService.getByNamaPerusahaan(nama)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("perusahaan/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByNamaAndPagePerusahaan(@QueryParam("namaPerusahaan") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerDokumenService.getByNamaPerusahaanAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("perusahaan/id")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByIdPerusahaan(@QueryParam("idPerusahaan") String id) {
		return registerDokumenService.getByIdPerusahaan(id)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("perusahaan/id/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByIdAndPagePerusahaan(@QueryParam("idPerusahaan") String id,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerDokumenService.getByIdPerusahaanAndPage(id, page, pageSize)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("dokumen/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByNamaDokumen(@QueryParam("namaDokumen") String nama) {
		return registerDokumenService.getByNamaDokumen(nama)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("dokumen/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByNamaAndPageDokumen(@QueryParam("namaDokumen") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerDokumenService.getByNamaDokumenAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("dokumen/id")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByIdDokumen(@QueryParam("idDokumen") String id) {
		return registerDokumenService.getByIdDokumen(id)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("dokumen/id/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<RegisterDokumenDTO> getByIdAndPageDokumen(@QueryParam("idDokumen") String id,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return registerDokumenService.getByIdDokumenAndPage(id, page, pageSize)
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}

}
