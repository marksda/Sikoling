package org.Sikoling.main.restful.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.permohonan.IKategoriPermohonanSuratArahanService;
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
@Path("jenis_permohonan_surat_arahan")
public class JenisPermohonanSuratArahanController {
	
	@Inject
	private IKategoriPermohonanSuratArahanService kategoriPermohonanSuratArahanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public JenisPermohonanSuratArahanDTO save(JenisPermohonanSuratArahanDTO d) {				
		return new JenisPermohonanSuratArahanDTO(
				kategoriPermohonanSuratArahanService.save(d.toJenisPermohonanSuratArahan())
				);
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public JenisPermohonanSuratArahanDTO update(JenisPermohonanSuratArahanDTO d) {		
		return new JenisPermohonanSuratArahanDTO(kategoriPermohonanSuratArahanService.update(d.toJenisPermohonanSuratArahan()));
	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(kategoriPermohonanSuratArahanService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<JenisPermohonanSuratArahanDTO> getAll() {
		return kategoriPermohonanSuratArahanService.getAll()
				.stream()
				.map(t -> new JenisPermohonanSuratArahanDTO(t))
				.collect(Collectors.toList());
	}

}
