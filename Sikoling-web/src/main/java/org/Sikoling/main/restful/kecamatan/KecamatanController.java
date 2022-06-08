package org.Sikoling.main.restful.kecamatan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kecamatan.IServiceKecamatan;
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
@Path("kecamatan")
public class KecamatanController {
	
	@Inject
	private IServiceKecamatan serviceKecamatan;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KecamatanDTO save(KecamatanDTO kecamatanDTO) {
		return new KecamatanDTO(serviceKecamatan.save(kecamatanDTO.toKecamatan()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KecamatanDTO update(KecamatanDTO kecamatanDTO) {
		return new KecamatanDTO(serviceKecamatan.update(kecamatanDTO.toKecamatan()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getAll() {
		return serviceKecamatan.getAll()
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return serviceKecamatan.getAllByPage(page, pageSize)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}

}
