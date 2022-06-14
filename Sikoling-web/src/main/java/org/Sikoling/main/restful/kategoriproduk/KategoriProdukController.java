package org.Sikoling.main.restful.kategoriproduk;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kategoriproduk.IKategoriProdukService;

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
public class KategoriProdukController {
	
	@Inject
	private IKategoriProdukService kategoriProdukService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriProdukDTO save(KategoriProdukDTO d) {
		return new KategoriProdukDTO(kategoriProdukService.save(d.toKategoriProduk()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriProdukDTO update(KategoriProdukDTO d) {
		return new KategoriProdukDTO(kategoriProdukService.update(d.toKategoriProduk()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriProdukDTO> getAll() {
		return kategoriProdukService.getAll()
				.stream()
				.map(t -> new KategoriProdukDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriProdukDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kategoriProdukService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new KategoriProdukDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriProdukDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return kategoriProdukService.getByQueryNama(nama)
				.stream()
				.map(t -> new KategoriProdukDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriProdukDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama, 
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kategoriProdukService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new KategoriProdukDTO(t))
				.collect(Collectors.toList());
	}

}
