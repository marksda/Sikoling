package org.Sikoling.main.restful.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.dokumen.IKbliService;
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
@Path("kbli")
public class KbliController {
	
	@Inject
	private IKbliService kbliService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KbliDTO save(KbliDTO t) {
		return new KbliDTO(kbliService.save(t.toKbli()));
	}
	
	@Path("{kode}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KbliDTO update(@PathParam("kode") String kode, KbliDTO t) {
		return new KbliDTO(kbliService.updateById(kode, t.toKbli()));
	}
	
	@Path("{kode}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("kode") String kode) {
		return new DeleteResponseDTO(kbliService.delete(kode));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<KbliDTO> getAll() {
		return kbliService.getAll()
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kbliService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByNama(@QueryParam("nama") String nama) {
		return kbliService.getByNama(nama)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kbliService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("kode")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByKode(@QueryParam("kode") String kode) {
		return kbliService.getByKode(kode)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("kode/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByKodeAndPage(@QueryParam("kode") String kode,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kbliService.getByKodeAndPage(kode, page, pageSize)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}

	@Path("kategori")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByKategori(@QueryParam("kategori") String kategori) {
		return kbliService.getByKategori(kategori)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("kategori/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KbliDTO> getByKategoriAndPage(@QueryParam("kategori") String kategori,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kbliService.getByKategoriAndPage(kategori, page, pageSize)
				.stream()
				.map(t -> new KbliDTO(t))
				.collect(Collectors.toList());
	}

}
