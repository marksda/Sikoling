package org.Sikoling.main.restful.kecamatan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.kecamatan.IKecamatanService;
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
@Path("kecamatan")
public class KecamatanController {
	
	@Inject
	private IKecamatanService kecamatanService;
	
	@Path("{idKabupaten}")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KecamatanDTO save(KecamatanDTO kecamatanDTO, @PathParam("idKabupaten") String idKabupaten) {
		return new KecamatanDTO(kecamatanService.save(kecamatanDTO.toKecamatan(), idKabupaten));
	}
	
	@Path("{idKabupaten}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public KecamatanDTO update(KecamatanDTO kecamatanDTO, @PathParam("idKabupaten") String idKabupaten) {
		return new KecamatanDTO(kecamatanService.update(kecamatanDTO.toKecamatan(), idKabupaten));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getAll() {
		return kecamatanService.getAll()
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kecamatanService.getAllByPage(page, pageSize)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getByNama(@QueryParam("nama") String nama) {
		return kecamatanService.getByNama(nama)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getByNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, 
			@QueryParam("pageSize") Integer pageSize) {
		return kecamatanService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("kabupaten")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getByKabupaten(@QueryParam("idKabupaten") String idKabupaten) {
		return kecamatanService.getByKabupaten(idKabupaten)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("kabupaten/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getByKabupatenAndPage(@QueryParam("idKabupaten") String idKabupaten, @QueryParam("page") Integer page, 
			@QueryParam("pageSize") Integer pageSize) {
		return kecamatanService.getByKabupatenAndPage(idKabupaten, page, pageSize)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("kabupaten/nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getByKabupatenAndNama(@QueryParam("idKabupaten") String idKabupaten, 
			@QueryParam("idKabupaten") String nama) {
		return kecamatanService.getByKabupatenAndNama(idKabupaten, nama)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}
	
	@Path("kabupaten/nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<KecamatanDTO> getByKabupatenAndQueryNamaAndPage(@QueryParam("idKabupaten") String idKabupaten, 
			@QueryParam("idKabupaten") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return kecamatanService.getByKabupatenAndNamaAndPage(idKabupaten, nama, page, pageSize)
				.stream()
				.map(k -> new KecamatanDTO(k))
				.collect(Collectors.toList());
	}

}