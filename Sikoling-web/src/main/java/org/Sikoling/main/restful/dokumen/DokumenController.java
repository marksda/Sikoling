package org.Sikoling.main.restful.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.dokumen.IDokumenService;
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
@Path("dokumen")
public class DokumenController {
	
	@Inject
	private IDokumenService dokumenService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DokumenDTO save(DokumenDTO d) {
		return new DokumenDTO(dokumenService.save(d.toDokumen()));
	}
	
	@Path("{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DokumenDTO update(@PathParam("id") String id, DokumenDTO d) {
		return new DokumenDTO(dokumenService.updateById(id, d.toDokumen()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(dokumenService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DokumenDTO> getAll() {
		return dokumenService.getAll()
				.stream()
				.map(t -> new DokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DokumenDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return dokumenService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new DokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DokumenDTO> getByNama(@QueryParam("nama") String nama) {
		return dokumenService.getByNama(nama)
				.stream()
				.map(t -> new DokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DokumenDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return dokumenService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new DokumenDTO(t))
				.collect(Collectors.toList());
	}
	
}
