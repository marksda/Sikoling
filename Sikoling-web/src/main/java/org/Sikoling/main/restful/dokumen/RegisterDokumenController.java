package org.Sikoling.main.restful.dokumen;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterDokumenService;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.apache.commons.io.FilenameUtils;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("register_dokumen")
public class RegisterDokumenController {
	
	@Inject
	private IRegisterDokumenService registerDokumenService;
	
	@Inject
	private IOtoritasService authorityService;
	
	@Inject
	private ILocalStorageService localStorageService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterDokumenDTO save(RegisterDokumenDTO d, @Context SecurityContext securityContext) throws IOException {	
		
		if(d == null) {
			throw new IOException("format register dokumen salah");
		}
		
		try {
			String source = d.getLokasiFile();
			String pathBaru = FilenameUtils.getFullPathNoEndSeparator(source);
			String destination= pathBaru.substring(0, pathBaru.lastIndexOf(File.separator)+1).concat(FilenameUtils.getName(source));			
			localStorageService.move(source, destination);
			
			String dirHisSource = source.concat("-his");
			String dirHisDestination = pathBaru.substring(0, pathBaru.lastIndexOf(File.separator));
			localStorageService.moveDir(dirHisSource, dirHisDestination);					
					
			Otoritas kreator = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
			d.setUploader(new OtoritasDTO(kreator));
			LocalDate tanggalRegistrasi = LocalDate.now();
			d.setTanggalRegistrasi(tanggalRegistrasi);
			d.setStatusVerified(Boolean.FALSE);
			d.setLokasiFile(destination);
			return new RegisterDokumenDTO(registerDokumenService.save(d.toRegisterDokumen()));
		}
		catch (Exception e) {
			throw new IOException("Save error");
		}	
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterDokumenDTO update(RegisterDokumenDTO d) {
		return new RegisterDokumenDTO(registerDokumenService.update(d.toRegisterDokumen()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterDokumenDTO updateId(@PathParam("idLama") String idLama, RegisterDokumenDTO d) throws IOException {
		return new RegisterDokumenDTO(registerDokumenService.updateId(idLama, d.toRegisterDokumen()));
	}

	@Path("{idRegisterDokumen}")
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN})
	public RegisterDokumenDTO delete(@PathParam("idRegisterDokumen") String idRegisterDokumen) throws IOException {
		RegisterDokumenDTO d = new RegisterDokumenDTO();
		d.setId(idRegisterDokumen);
		
		try {
			RegisterDokumenDTO hasil = new RegisterDokumenDTO(registerDokumenService.delete(d.toRegisterDokumen()));
			localStorageService.delete(FilenameUtils.getName(hasil.getLokasiFile()), FilenameUtils.getFullPathNoEndSeparator(hasil.getLokasiFile()));
			return hasil;
		}
		catch (Exception e) {
			throw new IOException("Delete error");
		}		
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterDokumenDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return registerDokumenService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new RegisterDokumenDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getJumlahData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return registerDokumenService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
