package org.Sikoling.main.restful.jabatan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.jabatan.IJabatanService;

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
@Path("jabatan")
public class JabatanController {
	
	@Inject
	private IJabatanService jabatanService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JabatanDTO save(JabatanDTO jabatanDTO) {
		return new JabatanDTO(jabatanService.save(jabatanDTO.toJabatan()));
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JabatanDTO update(JabatanDTO jabatanDTO) {
		return new JabatanDTO(jabatanService.update(jabatanDTO.toJabatan()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JabatanDTO> getAll() {
		return jabatanService.getAll()
				.stream()
				.map(t -> new JabatanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JabatanDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return jabatanService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new JabatanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JabatanDTO> getAllByPage(@QueryParam("nama") String nama) {
		return jabatanService.getByQueryNama(nama)
				.stream()
				.map(t -> new JabatanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JabatanDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return jabatanService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new JabatanDTO(t))
				.collect(Collectors.toList());
	}
	
	

}
