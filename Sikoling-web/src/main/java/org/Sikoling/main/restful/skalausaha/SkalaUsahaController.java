package org.Sikoling.main.restful.skalausaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.skalausaha.ISkalaUsahaService;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
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
@Path("skala_usaha")
public class SkalaUsahaController {

	@Inject
	private ISkalaUsahaService skalaUsahaService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public SkalaUsahaDTO save(SkalaUsahaDTO d) {
        return new SkalaUsahaDTO(skalaUsahaService.save(d.toSkalaUsaha()));
    }
	
	@Path("{id}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public SkalaUsahaDTO update(@PathParam("id") String id, SkalaUsahaDTO d) {
		return new SkalaUsahaDTO(skalaUsahaService.update(d.toSkalaUsaha()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<SkalaUsahaDTO> getAll() {
        return skalaUsahaService.getALL()
                .stream()
                .map(t -> new SkalaUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<SkalaUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return skalaUsahaService.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new SkalaUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<SkalaUsahaDTO> getByNama(@QueryParam("nama") String nama) {
        return skalaUsahaService.getByNama(nama)
                .stream()
                .map(t -> new SkalaUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<SkalaUsahaDTO> getByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return skalaUsahaService.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new SkalaUsahaDTO(t))
                .collect(Collectors.toList());
    }
	
}
