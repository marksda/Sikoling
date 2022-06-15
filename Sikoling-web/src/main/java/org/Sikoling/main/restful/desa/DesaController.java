package org.Sikoling.main.restful.desa;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.desa.IDesaService;

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
@Path("desa")
public class DesaController {
	
	@Inject
	private IDesaService desaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DesaDTO save(DesaDTO desaDTO) {
		return new DesaDTO(desaService.save(desaDTO.toDesa()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DesaDTO update(DesaDTO desaDTO) {
		return new DesaDTO(desaService.update(desaDTO.toDesa()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getAll() {
		return desaService.getAll()
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return desaService.getAllByPage(page, pageSize)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getByNama(@QueryParam("nama") String nama) {
		return desaService.getByNama(nama)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getByQueryAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return desaService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kecamatan")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getByKecamatan(@QueryParam("idKecamatan") String idKecamatan) {
		return desaService.getByKecamatan(idKecamatan)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kecamatan/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getByKecamatanAndPage(@QueryParam("idKecamatan") String idKecamatan,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return desaService.getByKecamatanAndPage(idKecamatan, page, pageSize)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kecamatan/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getByKecamatanAndNama(@QueryParam("idKecamatan") String idKecamatan, @QueryParam("nama") String nama) {
		return desaService.getByKecamatanAndNama(idKecamatan, nama)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kecamatan/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<DesaDTO> getByKecamatanAndNama(@QueryParam("idKecamatan") String idKecamatan, @QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return desaService.getByKecamatanAndNamaAndPage(idKecamatan, nama, page, pageSize)
				.stream()
				.map(d -> new DesaDTO(d))
				.collect(Collectors.toList());
	}

}
