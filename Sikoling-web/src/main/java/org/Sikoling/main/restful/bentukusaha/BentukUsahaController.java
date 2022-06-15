package org.Sikoling.main.restful.bentukusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.bentukusaha.IBentukUsahaService;
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
@Path("bentuk_usaha")
public class BentukUsahaController {
	
	@Inject
	private IBentukUsahaService bentukUsahaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BentukUsahaDTO save(BentukUsahaDTO bentukUsahaDTO) {
		return new BentukUsahaDTO(bentukUsahaService.save(bentukUsahaDTO.toBentukUsaha()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BentukUsahaDTO update(BentukUsahaDTO bentukUsahaDTO) {
		return new BentukUsahaDTO(bentukUsahaService.update(bentukUsahaDTO.toBentukUsaha()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getAll() {
		return bentukUsahaService.getAll()
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getAllByPage(page, pageSize)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByNama(@QueryParam("nama") String nama) {
		return bentukUsahaService.getByNama(nama)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kelompok")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByPelakuUsaha(@QueryParam("idKelompok") String idKelompok) {
		return bentukUsahaService.getByPelakuUsaha(idKelompok)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kelompok/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByPelakuUsahaAndPage(@QueryParam("idKelompok") String idKelompok,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getByPelakuUsahaAndPage(idKelompok, page, pageSize)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kelompok/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByPelakuUsahaAndNama(@QueryParam("idKelompok") String idKelompok, @QueryParam("nama") String nama) {
		return bentukUsahaService.getByPelakuUsahaAndNama(idKelompok, nama)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("kelompok/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByPelakuUsahaAndNamaAndPage(@QueryParam("idKelompok") String idKelompok,
			@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getByPelakuUsahaAndNamaAndPage(idKelompok, nama, page, pageSize)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}

}
