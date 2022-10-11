package org.Sikoling.main.restful.pelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.jenispelakuusaha.IJenisPelakuUsahaService;
import org.Sikoling.ejb.abstraction.service.pelakuusaha.IDetailPelakuUsahaServices;
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
	private IJenisPelakuUsahaService jenisPelakuUsahaService;
	
	@Inject
	private IDetailPelakuUsahaServices detailPelakuUsahaServices;
	
	@Path("jenis")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JenisPelakuUsahaDTO save(JenisPelakuUsahaDTO d) {
        return new JenisPelakuUsahaDTO(jenisPelakuUsahaService.save(d.toJenisPelakuUsaha()));
    }
	
	@Path("detail")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public DetailPelakuUsahaDTO saveDetail(DetailPelakuUsahaDTO d) {		
        return new DetailPelakuUsahaDTO(detailPelakuUsahaServices.save(d.toDetailPelakuUsaha()));
    }
	
	@Path("jenis")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JenisPelakuUsahaDTO update(JenisPelakuUsahaDTO d) {
		return new JenisPelakuUsahaDTO(jenisPelakuUsahaService.update(d.toJenisPelakuUsaha()));
	}
	
	@Path("detail")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DetailPelakuUsahaDTO updateDetail(DetailPelakuUsahaDTO d) {		
		return new DetailPelakuUsahaDTO(detailPelakuUsahaServices.update(d.toDetailPelakuUsaha()));
	}

	@Path("jenis")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JenisPelakuUsahaDTO> getAll() {
        return jenisPelakuUsahaService.getAll()
                .stream()
                .map(t -> new JenisPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetailPelakuUsahaDTO> getAllDetail() {
        return detailPelakuUsahaServices.getALL()
                .stream()
                .map(t -> new DetailPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("jenis/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JenisPelakuUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return jenisPelakuUsahaService.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new JenisPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetailPelakuUsahaDTO> getDetailAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return detailPelakuUsahaServices.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new DetailPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("jenis/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JenisPelakuUsahaDTO> getByNama(@QueryParam("nama") String nama) {
        return jenisPelakuUsahaService.getByNama(nama)
                .stream()
                .map(t -> new JenisPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetailPelakuUsahaDTO> getDetailByNama(@QueryParam("nama") String nama) {
        return detailPelakuUsahaServices.getByNama(nama)
                .stream()
                .map(t -> new DetailPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("jenis/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JenisPelakuUsahaDTO> getByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return jenisPelakuUsahaService.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new JenisPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("detail/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetailPelakuUsahaDTO> getDetailByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return detailPelakuUsahaServices.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new DetailPelakuUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
}
