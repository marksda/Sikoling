package org.Sikoling.main.restful.produk;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.produk.IProdukService;

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
@Path("Produk")
public class ProdukController {

	@Inject
	private IProdukService produkService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public ProdukDTO save(ProdukDTO d) {
		return new ProdukDTO(produkService.save(d.toProduk()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public ProdukDTO update(ProdukDTO d) {
		return new ProdukDTO(produkService.update(d.toProduk()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<ProdukDTO> getAll() {
		return produkService.getAll()
				.stream()
				.map(t -> new ProdukDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<ProdukDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return produkService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new ProdukDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<ProdukDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return produkService.getByQueryNama(nama)
				.stream()
				.map(t -> new ProdukDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<ProdukDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return produkService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new ProdukDTO(t))
				.collect(Collectors.toList());
	}
	
}
