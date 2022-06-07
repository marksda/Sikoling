package org.Sikoling.main.restful.propinsi;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.propinsi.IServicePropinsi;

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
	private IServicePropinsi servicePropinsi;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PropinsiDTO save(PropinsiDTO propinsi) {
        return new PropinsiDTO(servicePropinsi.save(propinsi.toPropinsi()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PropinsiDTO update(PropinsiDTO propinsi) {
        return new PropinsiDTO(servicePropinsi.update(propinsi.toPropinsi()));
    }
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getAll() {
        return servicePropinsi.getAll()
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return servicePropinsi.getAllByPage(page, pageSize)
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getByQueryNama(@QueryParam("nama") String nama) {
        return servicePropinsi.getByQueryNama(nama)
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
	@Path("page/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<PropinsiDTO> getByPageAndQueryNama(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return servicePropinsi.getByPageAndQueryNama(nama, page, pageSize)
                .stream()
                .map(f -> new PropinsiDTO(f))
                .collect(Collectors.toList());
    }
	
}
