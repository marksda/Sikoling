package org.Sikoling.main.restful.penanggungjawab;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.penanggungjawab.IPenanggungJawabService;
import org.Sikoling.main.restful.pemrakarsa.PenanggungJawabDTO;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
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
@Path("penanggung_jawab")
public class PersonController {
	
	@Inject
	private IPenanggungJawabService penanggungJawabService;
	
	@Path("{idPemrakarsa}")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PenanggungJawabDTO save(PenanggungJawabDTO pj, @PathParam("idPemrakarsa") String idPemrakarsa) {
		return new PenanggungJawabDTO(penanggungJawabService.save(pj.toPenanggungJawab(), idPemrakarsa));
	}
	
	@Path("{idPemrakarsa}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PenanggungJawabDTO update(PenanggungJawabDTO pj, @PathParam("idPemrakarsa") String idPemrakarsa) {
		return new PenanggungJawabDTO(penanggungJawabService.update(pj.toPenanggungJawab(), idPemrakarsa));
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
		return penanggungJawabService.getByNama(nama)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PenanggungJawabDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return penanggungJawabService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PenanggungJawabDTO(t))
				.collect(Collectors.toList());
	}
		
}
