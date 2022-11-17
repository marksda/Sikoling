package org.Sikoling.main.restful.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.dokumen.IKategoriDokumenService;
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
@Path("kategori_dokumen")
public class KategoriDokumenController {
	
	@Inject
	private IKategoriDokumenService kategoriDokumenService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriDokumenDTO save(KategoriDokumenDTO d) {
		return new KategoriDokumenDTO(kategoriDokumenService.save(d.toKategoriDokumen()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriDokumenDTO update(@QueryParam("id") String id, KategoriDokumenDTO d) {
		return new KategoriDokumenDTO(kategoriDokumenService.updateById(id, d.toKategoriDokumen()));
	}
	
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@QueryParam("id") String id) {
		return new DeleteResponseDTO(kategoriDokumenService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriDokumenDTO> getAll() {
		return kategoriDokumenService.getAll()
				.stream()
				.map(t -> new KategoriDokumenDTO(t))
				.collect(Collectors.toList());
	}

	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriDokumenDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kategoriDokumenService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new KategoriDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriDokumenDTO> getByNama(@QueryParam("nama") String nama) {
		return kategoriDokumenService.getByNama(nama)
				.stream()
				.map(t -> new KategoriDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriDokumenDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kategoriDokumenService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new KategoriDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
}
