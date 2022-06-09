package org.Sikoling.main.restful.propinsi;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.propinsi.IPropinsiService;

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
@Path("propinsi")
public class PropinsiController {
	
	@Inject
	private IPropinsiService propinsiService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PropinsiDTO save(PropinsiDTO propinsiDTO) {
        return new PropinsiDTO(propinsiService.save(propinsiDTO.toPropinsi()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PropinsiDTO update(PropinsiDTO propinsi) {
        return new PropinsiDTO(propinsiService.update(propinsi.toPropinsi()));
    }
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getAll() {
        return propinsiService.getAll()
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return propinsiService.getAllByPage(page, pageSize)
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getByQueryNama(@QueryParam("nama") String nama) {
        return propinsiService.getByQueryNama(nama)
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return propinsiService.getByQueryNamaAndPage(nama, page, pageSize)
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
}
