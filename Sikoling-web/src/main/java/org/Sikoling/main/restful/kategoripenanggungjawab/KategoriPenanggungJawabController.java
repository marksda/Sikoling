package org.Sikoling.main.restful.kategoripenanggungjawab;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kategoripenanggungjawab.IKategoriPenanggungJawabService;

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
@Path("Kategori_penanggung_jawab")
public class KategoriPenanggungJawabController {
	
	@Inject
	private IKategoriPenanggungJawabService kategoriPenanggungJawabService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriPenanggungJawabDTO save(KategoriPenanggungJawabDTO k) {
		return new KategoriPenanggungJawabDTO(kategoriPenanggungJawabService.save(k.toKategoriPenanggungJawab()));
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriPenanggungJawabDTO update(KategoriPenanggungJawabDTO k) {
		return new KategoriPenanggungJawabDTO(kategoriPenanggungJawabService.update(k.toKategoriPenanggungJawab()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriPenanggungJawabDTO> getAll() {
		return kategoriPenanggungJawabService.getAll()
				.stream()
				.map(t -> new KategoriPenanggungJawabDTO(t))
				.collect(Collectors.toList());
				
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriPenanggungJawabDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kategoriPenanggungJawabService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new KategoriPenanggungJawabDTO(t))
				.collect(Collectors.toList());				
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriPenanggungJawabDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return kategoriPenanggungJawabService.getByQueryNama(nama)
				.stream()
				.map(t -> new KategoriPenanggungJawabDTO(t))
				.collect(Collectors.toList());				
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KategoriPenanggungJawabDTO> getByQueryNama(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kategoriPenanggungJawabService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new KategoriPenanggungJawabDTO(t))
				.collect(Collectors.toList());				
	}

}
