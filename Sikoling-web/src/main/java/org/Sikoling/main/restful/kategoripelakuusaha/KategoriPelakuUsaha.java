package org.Sikoling.main.restful.kategoripelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kategoripelakuusaha.IKategoriPelakuUsahaServices;
import org.Sikoling.main.restful.response.DeleteResponseDTO;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("kategori_pelaku_usaha")
public class KategoriPelakuUsaha {

	@Inject
	private IKategoriPelakuUsahaServices kategoriPelakuUsahaService;	

	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public KategoriPelakuUsahaDTO saveKategori(KategoriPelakuUsahaDTO d) {
        return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.save(d.toKategoriPelakuUsaha()));
    }

	@Path("{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KategoriPelakuUsahaDTO updateKategori(@PathParam("id") String id, KategoriPelakuUsahaDTO d) {
		return new KategoriPelakuUsahaDTO(kategoriPelakuUsahaService.updateById(id, d.toKategoriPelakuUsaha()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(kategoriPelakuUsahaService.delete(id));
	}

	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getAllKategori() {
        return kategoriPelakuUsahaService.getALL()
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("skala_usaha/{idSkalaUsaha}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getAllKategoriBySkalaUsaha(@PathParam("idSkalaUsaha") String idSkalaUsaha) {
        return kategoriPelakuUsahaService.getALLBySkalaUsaha(idSkalaUsaha)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getAllKategoriByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return kategoriPelakuUsahaService.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getKategoriByNama(@QueryParam("nama") String nama) {
        return kategoriPelakuUsahaService.getByNama(nama)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<KategoriPelakuUsahaDTO> getKategoriByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return kategoriPelakuUsahaService.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new KategoriPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
}
