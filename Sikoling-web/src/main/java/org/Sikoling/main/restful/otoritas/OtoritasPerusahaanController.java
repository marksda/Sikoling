package org.Sikoling.main.restful.otoritas;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasPerusahaanService;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
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
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("otoritas_perusahaan")
public class OtoritasPerusahaanController {
	
	@Inject
	private IOtoritasPerusahaanService autorityPerusahaanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
    public OtoritasPerusahaanDTO save(OtoritasPerusahaanDTO d) throws IOException {
        return new OtoritasPerusahaanDTO(autorityPerusahaanService.save(d.toOtoritasPerusahaan()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public OtoritasPerusahaanDTO update(OtoritasPerusahaanDTO d) {		
		return new OtoritasPerusahaanDTO(autorityPerusahaanService.update(d.toOtoritasPerusahaan()));
	}
	
	@Path("id/{idLamaAutority}/{idLamaRegisterPerusahaan}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public OtoritasPerusahaanDTO updateId(@PathParam("idLamaAutority") String idLamaAutority,
			@PathParam("idLamaRegisterPerusahaan") String idLamaRegisterPerusahaan, OtoritasPerusahaanDTO d) throws IOException {
		return new OtoritasPerusahaanDTO(autorityPerusahaanService.updateId(idLamaAutority, idLamaRegisterPerusahaan, d.toOtoritasPerusahaan()));
	}
	
	@Path("{idOtoritas}/{idRegisterPerusahaan}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public OtoritasPerusahaanDTO delete(@PathParam("idOtoritas") String idOtoritas,	@PathParam("idRegisterPerusahaan") String idRegisterPerusahaan) throws IOException {
		OtoritasPerusahaanDTO dt = new OtoritasPerusahaanDTO();
		OtoritasDTO dtOtoritas = new OtoritasDTO();
		dtOtoritas.setId(idOtoritas);
		RegisterPerusahaanDTO dtRegPerusahaan = new RegisterPerusahaanDTO();
		dtRegPerusahaan.setId(idRegisterPerusahaan);
		dt.setOtoritas(dtOtoritas);
		dt.setRegisterPerusahaan(dtRegPerusahaan);
		
		return new OtoritasPerusahaanDTO(autorityPerusahaanService.delete(dt.toOtoritasPerusahaan()));
	}

	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public List<OtoritasPerusahaanDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return autorityPerusahaanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new OtoritasPerusahaanDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public Long getJumlahData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return autorityPerusahaanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
