package org.Sikoling.main.restful.kabupaten;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kabupaten.IKabupatenService;
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
@Path("kabupaten")
public class KabupatenController {
	
	@Inject
	private IKabupatenService kabupatenService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KabupatenDTO save(KabupatenDTO kabupatenDTO) {
		return new KabupatenDTO(kabupatenService.save(kabupatenDTO.toKabupaten()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KabupatenDTO update(KabupatenDTO kabupatenDTO) {
		return new KabupatenDTO(kabupatenService.update(kabupatenDTO.toKabupaten()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getAll() {
		return kabupatenService.getAll()
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kabupatenService.getAllByPage(page, pageSize)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return kabupatenService.getByQueryNama(nama)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getByQueryNamAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
			@QueryParam("pageSize") Integer pageSize) {
		return kabupatenService.getByQueryNamAndPage(nama, page, pageSize)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("propinsi")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getByIdPropinsi(@QueryParam("idPropinsi") String idPropinsi) {
		return kabupatenService.getByIdPropinsi(idPropinsi)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("propinsi/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getByIdPropinsiAndPage(@QueryParam("idPropinsi") String idPropinsi, @QueryParam("page") Integer page, 
			@QueryParam("pageSize") Integer pageSize) {
		return kabupatenService.getByIdPropinsiAndPage(idPropinsi, page, pageSize)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("propinsi/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getByIdPropinsiAndQueryNama(@QueryParam("idPropinsi") String idPropinsi, @QueryParam("nama") String nama) {
		return kabupatenService.getByIdPropinsiAndQueryNama(idPropinsi, nama)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("propinsi/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KabupatenDTO> getByIdPropinsiAndQueryNamaAndPage(@QueryParam("idPropinsi") String idPropinsi, @QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kabupatenService.getByIdPropinsiAndQueryNamaAndPage(idPropinsi, nama, page, pageSize)
				.stream()
				.map(k -> new KabupatenDTO(k))
				.collect(Collectors.toList());
	}
	
}
