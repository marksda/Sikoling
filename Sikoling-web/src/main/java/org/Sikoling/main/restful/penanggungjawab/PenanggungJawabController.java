package org.Sikoling.main.restful.penanggungjawab;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.penanggungjawab.IPenanggungJawabService;

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
@Path("penanggung_jawab")
public class PenanggungJawabController {
	
	@Inject
	private IPenanggungJawabService penanggungJawabService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PenanggungJawabDTO save(PenanggungJawabDTO pj) {
		return new PenanggungJawabDTO(penanggungJawabService.save(pj.toPenanggungJawab()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PenanggungJawabDTO update(PenanggungJawabDTO pj) {
		return new PenanggungJawabDTO(penanggungJawabService.update(pj.toPenanggungJawab()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getAll() {
		return penanggungJawabService.getAll()
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return penanggungJawabService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return penanggungJawabService.getByQueryNama(nama)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return penanggungJawabService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("pemilik")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getAllByPemilik(@QueryParam("idPemilik") String idPemilik) {
		return penanggungJawabService.getAllByPemilik(idPemilik)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("pemilik/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getAllByPemilikAndPage(@QueryParam("idPemilik") String idPemilik, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return penanggungJawabService.getAllByPemilikAndPage(idPemilik, page, pageSize)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("pemilik/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getAllByPemilikAndNama(@QueryParam("idPemilik") String idPemilik, @QueryParam("nama") String nama) {
		return penanggungJawabService.getAllByPemilikAndNama(idPemilik, nama)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("pemilik/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getAllByPemilikAndNamaAndPage(@QueryParam("idPemilik") String idPemilik, 
			@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return penanggungJawabService.getAllByPemilikAndNamaAndPage(idPemilik, nama, page, pageSize)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
}
