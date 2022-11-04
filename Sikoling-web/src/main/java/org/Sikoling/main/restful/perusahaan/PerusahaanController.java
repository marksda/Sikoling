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
	private IPerusahaanService perusahaanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PerusahaanDTO save(PerusahaanDTO d) {
		return new PerusahaanDTO(perusahaanService.save(d.toPerusahaan()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PerusahaanDTO update(PerusahaanDTO d) {
		return new PerusahaanDTO(perusahaanService.update(d.toPerusahaan()));
	}
	
	@Path("is_eksis")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	public Boolean getById(@QueryParam("id") String id) {
		return perusahaanService.getById(id);
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getAll() {
		return perusahaanService.getAll()
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return perusahaanService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByNama(@QueryParam("nama") String nama) {
		return perusahaanService.getByNama(nama)
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
		return perusahaanService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("creator")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PerusahaanDTO> getByCreator(@QueryParam("idCreator") String idCreator) {
		return perusahaanService.getByCreator(idCreator)
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
		return perusahaanService.getByCreatorAndPage(idCreator, page, pageSize)
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
		return perusahaanService.getByCreatorAndNama(idCreator, nama)
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
		return perusahaanService.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize)
				.stream()
				.map(t -> new PerusahaanDTO(t))
				.collect(Collectors.toList());
	}	
	
}
