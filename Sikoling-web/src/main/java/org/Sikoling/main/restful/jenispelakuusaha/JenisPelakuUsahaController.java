package org.Sikoling.main.restful.jenispelakuusaha;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.jenispelakuusaha.IJenisPelakuUsahaService;
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
@Path("jenis_pelaku_usaha")
public class JenisPelakuUsahaController {
	
	@Inject
	private IJenisPelakuUsahaService jenisPelakuUsahaService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JenisPelakuUsahaDTO save(JenisPelakuUsahaDTO jenisPelakuUsahaDTO) {
		return new JenisPelakuUsahaDTO(jenisPelakuUsahaService.save(jenisPelakuUsahaDTO.toJenisPelakuUsaha()));
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public JenisPelakuUsahaDTO update(JenisPelakuUsahaDTO jenisPelakuUsahaDTO) {
		return new JenisPelakuUsahaDTO(jenisPelakuUsahaService.update(jenisPelakuUsahaDTO.toJenisPelakuUsaha()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisPelakuUsahaDTO> getAll() {
		return jenisPelakuUsahaService.getAll()
				.stream()
				.map(t -> new JenisPelakuUsahaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisPelakuUsahaDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return jenisPelakuUsahaService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new JenisPelakuUsahaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisPelakuUsahaDTO> getByNama(@QueryParam("nama") String nama) {
		return jenisPelakuUsahaService.getByNama(nama)
				.stream()
				.map(t -> new JenisPelakuUsahaDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<JenisPelakuUsahaDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return jenisPelakuUsahaService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new JenisPelakuUsahaDTO(t))
				.collect(Collectors.toList());
	}

}
