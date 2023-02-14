package org.Sikoling.main.restful.pegawai;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.pegawai.IPegawaiPerusahaanService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;

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
@Path("pegawai_perusahaan")
public class PegawaiController {
	
	@Inject
	private IPegawaiPerusahaanService pegawaiPerusahaanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiPerusahaanDTO save(PegawaiPerusahaanDTO d) {		
		return new PegawaiPerusahaanDTO(pegawaiPerusahaanService.save(d.toPegawai()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PegawaiPerusahaanDTO update(PegawaiPerusahaanDTO d) {
		return new PegawaiPerusahaanDTO(pegawaiPerusahaanService.update(d.toPegawai()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PegawaiPerusahaanDTO> getAll() {
		return pegawaiPerusahaanService.getAll()
				.stream()
				.map(t -> new PegawaiPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PegawaiPerusahaanDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pegawaiPerusahaanService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PegawaiPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PegawaiPerusahaanDTO> getByNamaDokumen(@QueryParam("nama") String nama) {
		return pegawaiPerusahaanService.getByNama(nama)
				.stream()
				.map(t -> new PegawaiPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PegawaiPerusahaanDTO> getByNamaAndPage(@QueryParam("nama") String nama,
			@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return pegawaiPerusahaanService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PegawaiPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("idRegisterPerusahaan/{id}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PegawaiPerusahaanDTO> getByIdRegisterPerusahaan(@PathParam("id") String id) {
		List<PegawaiPerusahaanDTO> daftarPegawaiDTO =  
				pegawaiPerusahaanService.getByRegisterPerusahaan(id)
				.stream()
				.map(t -> new PegawaiPerusahaanDTO(t))
				.collect(Collectors.toList());
		
				
		return daftarPegawaiDTO;
	}

}
