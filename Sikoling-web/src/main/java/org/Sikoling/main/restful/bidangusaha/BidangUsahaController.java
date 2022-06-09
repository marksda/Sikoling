package org.Sikoling.main.restful.bidangusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.bidangusaha.IBidangUsahaService;
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
	private IBidangUsahaService bidangUsahaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BidangUsahaDTO save(BidangUsahaDTO bidangUsahaDTO) {
		return new BidangUsahaDTO(bidangUsahaService.save(bidangUsahaDTO.toBidangUsaha()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public BidangUsahaDTO update(BidangUsahaDTO bidangUsahaDTO) {
		return new BidangUsahaDTO(bidangUsahaService.update(bidangUsahaDTO.toBidangUsaha()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getAll() {
		return bidangUsahaService.getAll()
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bidangUsahaService.getAllByPage(page, pageSize)
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return bidangUsahaService.getByQueryNama(nama)
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<BidangUsahaDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return bidangUsahaService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(d -> new BidangUsahaDTO(d))
				.collect(Collectors.toList());
	}

}
