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
	public BentukUsahaDTO save(BentukUsahaDTO bidangUsahaDTO) {
		return new BentukUsahaDTO(bentukUsahaService.save(bidangUsahaDTO.toBentukUsaha()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BentukUsahaDTO update(BentukUsahaDTO bidangUsahaDTO) {
		return new BentukUsahaDTO(bentukUsahaService.update(bidangUsahaDTO.toBentukUsaha()));
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
	public List<BentukUsahaDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return bentukUsahaService.getByQueryNama(nama)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BentukUsahaDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(d -> new BentukUsahaDTO(d))
				.collect(Collectors.toList());
	}

}
