package org.Sikoling.main.restful.pemrakarsa;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.pemrakarsa.IPemrakarsaService;

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
@Path("pemrakarsa")
public class PemrakarsaController {
	
	@Inject
	private IPemrakarsaService pemrakarsaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PemrakarsaDTO save(PemrakarsaDTO d) {
		return new PemrakarsaDTO(pemrakarsaService.save(d.toPemrakarsa()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PemrakarsaDTO update(PemrakarsaDTO d) {
		return new PemrakarsaDTO(pemrakarsaService.update(d.toPemrakarsa()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getAll() {
		return pemrakarsaService.getAll()
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByNama(@QueryParam("nama") String nama) {
		return pemrakarsaService.getByNama(nama)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByCreator(@QueryParam("idCreator") String idCreator) {
		return pemrakarsaService.getByCreator(idCreator)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByCreatorAndPage(@QueryParam("idCreator") String idCreator,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getByCreatorAndPage(idCreator, page, pageSize)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByCreatorAndNama(@QueryParam("idCreator") String idCreator,
			@QueryParam("nama") String nama) {
		return pemrakarsaService.getByCreatorAndNama(idCreator, nama)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PemrakarsaDTO> getByCreatorAndNamaAndPage(@QueryParam("idCreator") String idCreator,
			@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pemrakarsaService.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize)
				.stream()
				.map(t -> new PemrakarsaDTO(t))
				.collect(Collectors.toList());
	}	
	
}
