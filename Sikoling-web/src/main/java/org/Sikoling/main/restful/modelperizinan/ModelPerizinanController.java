package org.Sikoling.main.restful.modelperizinan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.modelperizinan.IModelPerizinanService;
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
@Path("model_perizinan")
public class ModelPerizinanController {
	
	@Inject
	private IModelPerizinanService modelPerizinanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ModelPerizinanDTO save(ModelPerizinanDTO modelPerizinanDTO) {
        return new ModelPerizinanDTO(modelPerizinanService.save(modelPerizinanDTO.toModelPerizinan()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public ModelPerizinanDTO update(ModelPerizinanDTO modelPerizinanDTO) {
		return new ModelPerizinanDTO(modelPerizinanService.update(modelPerizinanDTO.toModelPerizinan()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<ModelPerizinanDTO> getAll() {
        return modelPerizinanService.getALL()
                .stream()
                .map(t -> new ModelPerizinanDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<ModelPerizinanDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return modelPerizinanService.getAllByPage(page, pageSize)
                .stream()
                .map(t -> new ModelPerizinanDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<ModelPerizinanDTO> getByNama(@QueryParam("nama") String nama) {
        return modelPerizinanService.getByNama(nama)
                .stream()
                .map(t -> new ModelPerizinanDTO(t))
                .collect(Collectors.toList());
    }
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<ModelPerizinanDTO> getByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
    		@QueryParam("pageSize") Integer pageSize) {
        return modelPerizinanService.getByNamaAndPage(nama, page, pageSize)
                .stream()
                .map(t -> new ModelPerizinanDTO(t))
                .collect(Collectors.toList());
    }
	
}
