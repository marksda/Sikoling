package org.Sikoling.main.restful.bidangUsaha;

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
@Path("bidang_usaha")
public class BidangUsahaController {
	
	@Inject
	private IBentukUsahaService bentukUsahaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BidangUsahaDTO save(BidangUsahaDTO bidangUsahaDTO) {
		return new BidangUsahaDTO(bentukUsahaService.save(bidangUsahaDTO.toBentukUsaha()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BidangUsahaDTO update(BidangUsahaDTO bidangUsahaDTO) {
		return new BidangUsahaDTO(bentukUsahaService.update(bidangUsahaDTO.toBentukUsaha()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getAll() {
		return bentukUsahaService.getAll()
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getAllByPage(page, pageSize)
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return bentukUsahaService.getByQueryNama(nama)
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bentukUsahaService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}

}
