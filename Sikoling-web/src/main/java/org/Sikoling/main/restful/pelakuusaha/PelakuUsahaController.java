package org.Sikoling.main.restful.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.pelakuusaha.IPelakuUsahaServices;
import org.Sikoling.ejb.abstraction.service.pelakuusaha.KategoriPelakuUsahaService;

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
@Path("pelaku_usaha")
public class PelakuUsahaController {
	
	@Inject
	private KategoriPelakuUsahaService kategoriPelakuUsahaService;
	
	@Inject
	private IPelakuUsahaServices pelakuUsahaServices;
	
	@Path("kategori")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public KategoriPelakuUsahaDTO save(KategoriPelakuUsahaDTO d) {
        return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.save(d.toKategoriPelakuUsaha()));
    }
	
	@Path("detail")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PelakuUsahaDTO saveDetail(PelakuUsahaDTO d) {		
        return new PelakuUsahaDTO(pelakuUsahaServices.save(d.toDetailPelakuUsaha()));
    }
	
	@Path("jenis")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriPelakuUsahaDTO update(KategoriPelakuUsahaDTO d) {
		return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.update(d.toKategoriPelakuUsaha()));
	}
	
	@Path("detail")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PelakuUsahaDTO updateDetail(PelakuUsahaDTO d) {		
		return new PelakuUsahaDTO(pelakuUsahaServices.update(d.toDetailPelakuUsaha()));
	}

	@Path("jenis")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getAll() {
        return kategoriPelakuUsahaService.getALL()
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getAllDetail() {
        return pelakuUsahaServices.getALL()
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("jenis/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return kategoriPelakuUsahaService.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getDetailAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return pelakuUsahaServices.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("jenis/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getByNama(@QueryParam("nama") String nama) {
        return kategoriPelakuUsahaService.getByNama(nama)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getDetailByNama(@QueryParam("nama") String nama) {
        return pelakuUsahaServices.getByNama(nama)
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("jenis/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return kategoriPelakuUsahaService.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getDetailByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return pelakuUsahaServices.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
}
