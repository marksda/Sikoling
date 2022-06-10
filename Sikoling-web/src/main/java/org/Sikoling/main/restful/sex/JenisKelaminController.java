package org.Sikoling.main.restful.sex;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.sex.IJenisKelaminService;

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
@Path("sex")
public class JenisKelaminController {
	
	@Inject
	private IJenisKelaminService jenisKelaminService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JenisKelaminDTO save(JenisKelaminDTO jenisKelaminDTO) {
		return new JenisKelaminDTO(jenisKelaminService.save(jenisKelaminDTO.toJenisKelamin()));
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JenisKelaminDTO update(JenisKelaminDTO jenisKelaminDTO) {
		return new JenisKelaminDTO(jenisKelaminService.update(jenisKelaminDTO.toJenisKelamin()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisKelaminDTO> getAll() {
		return jenisKelaminService.getAll()
				.stream()
				.map(t -> new JenisKelaminDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisKelaminDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return jenisKelaminService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new JenisKelaminDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisKelaminDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return jenisKelaminService.getByQueryNama(nama)
				.stream()
				.map(t -> new JenisKelaminDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisKelaminDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return jenisKelaminService.getByQueryNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new JenisKelaminDTO(t))
				.collect(Collectors.toList());
	}

}
