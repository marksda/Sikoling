package org.Sikoling.main.restful.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.permohonan.IPosisiTahapPemberkasanService;
import org.Sikoling.main.restful.response.DeleteResponseDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("posisi_tahap_Pemberkasan")
public class PosisiTahapPemberkasanController {
	
	@Inject
	private IPosisiTahapPemberkasanService posisiTahapPemberkasanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public PosisiTahapPemberkasanDTO save(PosisiTahapPemberkasanDTO d) {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.save(d.toPosisiTahapPemberkasan()));
		
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public PosisiTahapPemberkasanDTO update(PosisiTahapPemberkasanDTO d) {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.update(d.toPosisiTahapPemberkasan()));		
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(posisiTahapPemberkasanService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<PosisiTahapPemberkasanDTO> getAll() {
		return posisiTahapPemberkasanService.getAll()
				.stream()
				.map(t -> new PosisiTahapPemberkasanDTO(t))
				.collect(Collectors.toList());
	}

}
