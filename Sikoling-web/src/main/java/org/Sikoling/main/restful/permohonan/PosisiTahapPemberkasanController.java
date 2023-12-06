package org.Sikoling.main.restful.permohonan;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.permohonan.IPosisiTahapPemberkasanService;
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
@Path("posisi_tahap_pemberkasan")
public class PosisiTahapPemberkasanController {
	
	@Inject
	private IPosisiTahapPemberkasanService posisiTahapPemberkasanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
    public PosisiTahapPemberkasanDTO save(PosisiTahapPemberkasanDTO d) throws IOException {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.save(d.toPosisiTahapPemberkasan()));
    }
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public PosisiTahapPemberkasanDTO update(PosisiTahapPemberkasanDTO d) {		
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.update(d.toPosisiTahapPemberkasan()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public PosisiTahapPemberkasanDTO updateId(@PathParam("idLama") String idLama, PosisiTahapPemberkasanDTO d) throws IOException {
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.updateId(idLama, d.toPosisiTahapPemberkasan()));
	}
	
	@Path("{idPosisiTahapPemberkasan}")
	@DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR})
	public PosisiTahapPemberkasanDTO delete(@PathParam("idPosisiTahapPemberkasan") String idPosisiTahapPemberkasan) throws IOException {
		PosisiTahapPemberkasanDTO d = new PosisiTahapPemberkasanDTO();
		d.setId(idPosisiTahapPemberkasan);
		
		return new PosisiTahapPemberkasanDTO(posisiTahapPemberkasanService.delete(d.toPosisiTahapPemberkasan()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public List<PosisiTahapPemberkasanDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return posisiTahapPemberkasanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PosisiTahapPemberkasanDTO(t))
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
		
		return posisiTahapPemberkasanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
