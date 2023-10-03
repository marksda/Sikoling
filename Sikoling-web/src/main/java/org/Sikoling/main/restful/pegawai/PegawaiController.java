package org.Sikoling.main.restful.pegawai;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.service.pegawai.IPegawaiPerusahaanService;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.main.restful.jabatan.JabatanDTO;
import org.Sikoling.main.restful.person.PersonDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;
import org.Sikoling.main.restful.queryparams.FilterDTO;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.queryparams.SortOrderDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
@Path("pegawai_perusahaan")
public class PegawaiController {
	
	@Inject
	private IPegawaiPerusahaanService pegawaiPerusahaanService;
	
	@Inject
	private IPersonService personService;
	
	@Inject
	private ILocalStorageService localStorageService;	
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiDTO save(PegawaiDTO d) throws IOException {		
		return new PegawaiDTO(pegawaiPerusahaanService.save(d.toPegawai()));
	}
	
	@Path("add_direktur")
	@POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiDTO saveDirektur(@FormDataParam("personData") String personData, @FormDataParam("imageKtp") File imageKtp, 
			@FormDataParam("imageKtp") FormDataContentDisposition contentDisposition, @FormDataParam("registerPerusahaanData") String registerPerusahaanData) throws IOException {	
		Jsonb jsonb = JsonbBuilder.create();
		
		RegisterPerusahaanDTO registerPerusahaanDTO = jsonb.fromJson(registerPerusahaanData, RegisterPerusahaanDTO.class);
		
		PersonDTO personDTO = jsonb.fromJson(personData, PersonDTO.class);
		QueryParamFiltersDTO queryParamFiltersDTO = new QueryParamFiltersDTO();
		queryParamFiltersDTO.setPageNumber(1);
		queryParamFiltersDTO.setPageSize(0);
		
		List<FilterDTO> filterDTOs = new ArrayList<FilterDTO>();
		FilterDTO filterDTO = new FilterDTO();
		filterDTO.setFieldName("nik");
		filterDTO.setValue(personDTO.getNik());
		filterDTOs.add(filterDTO);		
		queryParamFiltersDTO.setFilters(filterDTOs);
		
		List<SortOrderDTO> sortOrderDTOs = new ArrayList<SortOrderDTO>();
		SortOrderDTO sortOrderDTO = new SortOrderDTO();
		sortOrderDTO.setFieldName("nik");
		sortOrderDTO.setValue("DESC");
		sortOrderDTOs.add(sortOrderDTO);
		queryParamFiltersDTO.setSortOrders(sortOrderDTOs);
		
		List<PersonDTO> personDTOs = personService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PersonDTO(t))
				.collect(Collectors.toList());
		
		PegawaiDTO d = new PegawaiDTO();	
		JabatanDTO jabatanDTO = new JabatanDTO();	
		jabatanDTO.setId("010");
		jabatanDTO.setNama("DIREKTUR");
		if(personDTOs.isEmpty()) {
			String namaFile = contentDisposition.getFileName();
			String extensionFile = FilenameUtils.getExtension(namaFile);
			String fileKey = UUID.randomUUID().toString()
					.concat("-").concat(personDTO.getNik()).concat(".").concat(extensionFile);
			personDTO.setScanKTP("/identitas_personal/".concat(fileKey));
			
			Person person = personService.save(personDTO.toPerson());
			
			if(person != null) {			
				try {
					InputStream uploadedInputStream = new FileInputStream(imageKtp);
					String subPathLocation = File.separator.concat("identitas_personal");
					localStorageService.upload(fileKey, uploadedInputStream, subPathLocation);
				} catch (IOException e) {
					throw new IOException("file gambar ktp tidak bisa disimpan");
				}
				
			}
			
			d.setRegisterPerusahaan(registerPerusahaanDTO);
			d.setPerson(new PersonDTO(person));		
			d.setJabatan(jabatanDTO);			
			return new PegawaiDTO(pegawaiPerusahaanService.save(d.toPegawai()));
		}
		else {	
			PersonDTO personDTOFromDatabase = personDTOs.get(0);
			if(personDTOFromDatabase.getStatusVerified().booleanValue()) {
				d.setRegisterPerusahaan(registerPerusahaanDTO);
				d.setPerson(personDTOFromDatabase);		
				d.setJabatan(jabatanDTO);			
				return new PegawaiDTO(pegawaiPerusahaanService.save(d.toPegawai()));
			}
			else {
				String fileLama = null;
				if(personDTO.getScanKTP() != "") {
					fileLama = personDTO.getScanKTP();
				}
				String fileKey = null;
				if(imageKtp != null) {
					String namaFile = contentDisposition.getFileName();
					String extensionFile = FilenameUtils.getExtension(namaFile);
					fileKey = UUID.randomUUID().toString()
							.concat("-").concat(personDTO.getNik()).concat(".").concat(extensionFile);
					personDTO.setScanKTP("/identitas_personal/".concat(fileKey));	
				}		
				
				Person person = personService.update(personDTO.toPerson());
				
				if(person != null) {		
					if(fileKey != null) {
						try {
							String subPathLocation = File.separator.concat("identitas_personal");
							if(personDTO.getScanKTP() != "") {
								String[] hasilSplit = fileLama.split("/");
								localStorageService.delete(hasilSplit[hasilSplit.length -1], subPathLocation); 
							}
							
							InputStream uploadedInputStream = new FileInputStream(imageKtp);					
							localStorageService.upload(fileKey, uploadedInputStream, subPathLocation);
						} catch (IOException e) {
							throw new IOException("file gambar ktp tidak bisa disimpan");
						}
					}		
				}
				else {
					throw new IOException("Gagal update data person");
				}

				d.setRegisterPerusahaan(registerPerusahaanDTO);
				d.setPerson(new PersonDTO(person));		
				d.setJabatan(jabatanDTO);			
				return new PegawaiDTO(pegawaiPerusahaanService.save(d.toPegawai()));
			}
		}
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiDTO update(PegawaiDTO d) {
		return new PegawaiDTO(pegawaiPerusahaanService.update(d.toPegawai()));
	}
	
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiDTO updateId(@PathParam("idLama") String idLama, PegawaiDTO d) throws IOException {
		return new PegawaiDTO(pegawaiPerusahaanService.updateId(idLama, d.toPegawai()));
	}
	
	@Path("{idPegawai}")
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PegawaiDTO delete(@PathParam("idPegawai") String idPegawai) throws IOException {
		PegawaiDTO d = new PegawaiDTO();
		d.setId(idPegawai);
		return new PegawaiDTO(pegawaiPerusahaanService.delete(d.toPegawai()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<PegawaiDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return pegawaiPerusahaanService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PegawaiDTO(t))
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
		
		return pegawaiPerusahaanService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
