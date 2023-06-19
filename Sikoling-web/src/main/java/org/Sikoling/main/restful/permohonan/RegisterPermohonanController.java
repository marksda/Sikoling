package org.Sikoling.main.restful.permohonan;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonanArahan;
import org.Sikoling.ejb.abstraction.service.authority.IAutorityService;
import org.Sikoling.ejb.abstraction.service.log.IFlowLogService;
import org.Sikoling.ejb.abstraction.service.permohonan.IRegisterPermohonanService;
import org.Sikoling.main.restful.autority.AutorityDTO;
import org.Sikoling.main.restful.log.FlowLogPermohonanDTO;
import org.Sikoling.main.restful.log.KategoriFlowLogDTO;
import org.Sikoling.main.restful.log.StatusFlowLogDTO;
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
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;

@Stateless
@LocalBean
@Path("register_permohonan")
public class RegisterPermohonanController {
	@Inject
	private IAutorityService authorityService;
	
	@Inject
	private IFlowLogService flowLogService;
	
	@Inject 
	private IRegisterPermohonanService registerPermohonanService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPermohonanDTO save(RegisterPermohonanDTO d, @Context SecurityContext securityContext) throws IOException {
		Autority pengurusPermohonan = authorityService.getByUserName(securityContext.getUserPrincipal().getName());
		AutorityDTO pengurusPermohonanDto = new AutorityDTO(pengurusPermohonan);
		d.setPengurusPermohonan(pengurusPermohonanDto);
		PosisiTahapPemberkasanDTO pengirimBerkasDTO = new PosisiTahapPemberkasanDTO();
		pengirimBerkasDTO.setId("0");
		d.setPengirimBerkas(pengirimBerkasDTO);
		PosisiTahapPemberkasanDTO penerimaBerkasDTO = new PosisiTahapPemberkasanDTO();
		penerimaBerkasDTO.setId("1");
		d.setPenerimaBerkas(penerimaBerkasDTO);
		StatusFlowLogDTO statusFlowLogDTO = new StatusFlowLogDTO();
		statusFlowLogDTO.setId("4");
		statusFlowLogDTO.setNama("BERKAS DI DLHK");
		d.setStatusFlowLog(statusFlowLogDTO);		
		LocalDate tanggalRegistrasi = LocalDate.now();
		d.setTanggalRegistrasi(tanggalRegistrasi);
		
		if(d instanceof RegisterPermohonanArahanDTO) {
			RegisterPermohonanArahanDTO s = (RegisterPermohonanArahanDTO) d;	
			RegisterPermohonanArahan registerPermohonanArahan = (RegisterPermohonanArahan) registerPermohonanService.save(s.toRegisterPermohonanArahan());
			
			FlowLogPermohonanDTO flowLogPermohonanDTO = new FlowLogPermohonanDTO();
			flowLogPermohonanDTO.setId(null);
			flowLogPermohonanDTO.setTanggal(tanggalRegistrasi);
			KategoriFlowLogDTO kategoriFlowLogDTO = new KategoriFlowLogDTO();
			kategoriFlowLogDTO.setId("1");
			kategoriFlowLogDTO.setNama("Permohonan");
			flowLogPermohonanDTO.setKategoriFlowLog(kategoriFlowLogDTO);
			flowLogPermohonanDTO.setPengirimBerkas(pengirimBerkasDTO);
			flowLogPermohonanDTO.setPenerimaBerkas(penerimaBerkasDTO);
			flowLogPermohonanDTO.setStatusFlowLog(statusFlowLogDTO);
			flowLogPermohonanDTO.setKeterangan("Berkas permohonan berhasil dimasukkan");
			flowLogPermohonanDTO.setPengakses(pengurusPermohonanDto);
			flowLogPermohonanDTO.setRegisterPermohonan(
					new RegisterPermohonanArahanDTO(registerPermohonanArahan)
					);			
			flowLogService.save(flowLogPermohonanDTO.toFlowLogPermohonan());
			
			return new RegisterPermohonanArahanDTO(registerPermohonanArahan);
		}
		else {
			return null;
		}		
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPermohonanDTO update(RegisterPermohonanDTO d) {		
		return new RegisterPermohonanDTO(registerPermohonanService.update(d.toRegisterPermohonan()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPermohonanDTO updateId(@PathParam("idLama") String idLama, RegisterPermohonanDTO d) throws IOException {
		return new RegisterPermohonanDTO(registerPermohonanService.updateId(idLama, d.toRegisterPermohonan()));
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public RegisterPermohonanDTO delete(RegisterPermohonanDTO d) throws IOException {
		return new RegisterPermohonanDTO(registerPermohonanService.delete(d.toRegisterPermohonan()));
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<RegisterPermohonanDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return registerPermohonanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new RegisterPermohonanDTO(t))
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
		
		return registerPermohonanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}

}
