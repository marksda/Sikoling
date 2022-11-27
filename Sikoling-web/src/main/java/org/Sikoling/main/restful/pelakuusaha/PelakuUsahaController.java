package org.Sikoling.main.restful.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.pelakuusaha.IPelakuUsahaServices;
import org.Sikoling.main.restful.kategoripelakuusaha.KategoriPelakuUsahaDTO;

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
	private IPelakuUsahaServices pelakuUsahaServices;
		
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PelakuUsahaDTO save(PelakuUsahaDTO d) {		
        return new PelakuUsahaDTO(pelakuUsahaServices.save(d.toPelakuUsaha()));
    }
		
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PelakuUsahaDTO update(PelakuUsahaDTO d) {		
		return new PelakuUsahaDTO(pelakuUsahaServices.update(d.toPelakuUsaha()));
	}
		
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getAll() {
        return pelakuUsahaServices.getALL()
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }

	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return pelakuUsahaServices.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
		
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getByNama(@QueryParam("nama") String nama) {
        return pelakuUsahaServices.getByNama(nama)
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
		
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return pelakuUsahaServices.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("by_kategori")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PelakuUsahaDTO> getPelakuUsahaByKategoriPelakuusaha(@QueryParam("idKategori") String idKategori) {
		KategoriPelakuUsahaDTO kategori = new KategoriPelakuUsahaDTO();
		kategori.setId(idKategori);
        return pelakuUsahaServices.getByKategoriPelakuUsaha(kategori.toKategoriPelakuUsaha())
                .stream()
                .map(t -> new PelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
}
