package org.Sikoling.main.restful.dokumen;

import org.Sikoling.ejb.abstraction.service.dokumen.IDokumenOssService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("dokumen_oss")
public class DokumenOssController {
	
	@Inject
	private IDokumenOssService dokumenOssService;
	
	@Path("{idRegistrasiDokumen}")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public DokumenOssDTO save(@PathParam("idRegistrasiDokumen") String idRegistrasiDokumen, DokumenOssDTO t) {
		return new DokumenOssDTO(dokumenOssService.save(t.toDokumenOss(), idRegistrasiDokumen));
	}

}
