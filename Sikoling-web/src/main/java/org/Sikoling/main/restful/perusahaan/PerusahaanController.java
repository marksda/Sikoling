package org.Sikoling.main.restful.perusahaan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.perusahaan.IPerusahaanService;

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
@Path("perusahaan")
public class PerusahaanController {
	
	@Inject
	private IPerusahaanService pemrakarsaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PerusahaanDTO save(PerusahaanDTO d) {
		return new PerusahaanDTO(pemrakarsaService.save(d.toPemrakarsa()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PerusahaanDTO update(PerusahaanDTO d) {
		return new PerusahaanDTO(pemrakarsaService.update(d.toPemrakarsa()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getAll() {
		return pemrakarsaService.getAll()
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByNama(@QueryParam("nama") String nama) {
		return pemrakarsaService.getByNama(nama)
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
		return pemrakarsaService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByCreator(@QueryParam("idCreator") String idCreator) {
		return pemrakarsaService.getByCreator(idCreator)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByCreatorAndPage(@QueryParam("idCreator") String idCreator,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getByCreatorAndPage(idCreator, page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByCreatorAndNama(@QueryParam("idCreator") String idCreator,
			@QueryParam("nama") String nama) {
		return pemrakarsaService.getByCreatorAndNama(idCreator, nama)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByCreatorAndNamaAndPage(@QueryParam("idCreator") String idCreator,
			@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}	
	
}
