package org.Sikoling.main.restful.files;

import java.io.IOException;
import java.io.InputStream;
import org.Sikoling.ejb.abstraction.service.file.IStorageService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("files")
public class FileController {	
	
	@Inject
	private IStorageService storageService;	
	
	@Path("onlyoffice")
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
//	@RequiredAuthorization
//	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
    public ResponseFeedback notifOnlyOffice(OnlyOfficeBodyRequestDTO d) {
		ResponseFeedback r = new ResponseFeedback();
		r.setError(0);
		return r;
    }
	
	@Path("onlyoffice/{namaFile}")
    @GET
    @Produces("application/msword")
//    @RequiredAuthorization
//	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
    public InputStream loadFile( @PathParam("namaFile") String namaFile) throws IOException {
		return storageService.load("onlyoffice", namaFile);
    }
		
	@Path("{subPath}/{namaFile}")
    @GET
    @RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
    public InputStream loadFileSecure(@PathParam("subPath") String subPath, @PathParam("namaFile") String namaFile) throws IOException {
		return storageService.load(subPath, namaFile);
    }
	
}