package org.Sikoling.main.restful.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
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
@Path("person")
public class PersonController {
	
	@Inject
	private IPersonService personService;
	
	@Inject
	private ILocalStorageService localStorageService;	
	
	@POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public PersonDTO save(@FormDataParam("personData") String personData, @FormDataParam("imageKtp") File imageKtp, 
			@FormDataParam("imageKtp") FormDataContentDisposition contentDisposition) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		PersonDTO personDTO = jsonb.fromJson(personData, PersonDTO.class);
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
				return new PersonDTO(person);
			} catch (IOException e) {
				throw new IOException("gambar tidak bisa disimpan");
			}
			
		}
		
		throw new IOException("data sudah ada");
	}
	
	@PUT
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public PersonDTO update(@FormDataParam("personData") String personData, 
			@FormDataParam("imageKtp") File imageKtp, @FormDataParam("imageKtp") FormDataContentDisposition contentDisposition) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		PersonDTO personDTO = jsonb.fromJson(personData, PersonDTO.class);	
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
					return new PersonDTO(person);
				} catch (IOException e) {
					throw new IOException("gambar tidak bisa disimpan");
				}
			}
			else {
				return new PersonDTO(person);
			}			
		}
		
		throw new IOException("Gagal update data person");
	}
		
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public PersonDTO updateId(@PathParam("idLama") String idLama, @FormDataParam("personData") String personData, 
			@FormDataParam("imageKtp") File imageKtp, @FormDataParam("imageKtp") FormDataContentDisposition contentDisposition) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		PersonDTO personDTO = jsonb.fromJson(personData, PersonDTO.class);	
		String fileLama = null;
		if(!personDTO.getScanKTP().equals("")) {
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
		
		Person person = personService.updateId(idLama, personDTO.toPerson());
		
		if(person != null) {		
			if(fileKey != null) {
				try {
					String subPathLocation = File.separator.concat("identitas_personal");
					if(fileLama != null) {
						String[] hasilSplit = fileLama.split("/");
						localStorageService.delete(hasilSplit[hasilSplit.length -1], subPathLocation); 
					}
					
					InputStream uploadedInputStream = new FileInputStream(imageKtp);					
					localStorageService.upload(fileKey, uploadedInputStream, subPathLocation);
					return new PersonDTO(person);
				} catch (IOException e) {
					throw new IOException("gambar tidak bisa disimpan");
				}
			}
			else {
				return new PersonDTO(person);
			}			
		}
		
		throw new IOException("data sudah ada");
	}
	
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public PersonDTO delete(@Context UriInfo info) throws IOException {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("dt");
		Jsonb jsonb = JsonbBuilder.create();
		PersonDTO personDTO = jsonb.fromJson(queryParamsStr, PersonDTO.class);
		
		String fileLama = null;
		if(!personDTO.getScanKTP().equals("")) {
			fileLama = personDTO.getScanKTP();
			String[] hasilSplit = fileLama.split("/");
			String subPathLocation = File.separator.concat("identitas_personal");
			localStorageService.delete(hasilSplit[hasilSplit.length -1], subPathLocation); 
		}		
		
		return new PersonDTO(personService.delete(personDTO.toPerson()));
	}
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public List<PersonDTO> getDaftarData(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return personService.getDaftarData(queryParamFiltersDTO.toQueryParamFilters())
				.stream()
				.map(t -> new PersonDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("count")
	@GET
    @Produces({MediaType.TEXT_PLAIN})
	@RequiredAuthorization
	@RequiredRole({Role.ADMINISTRATOR, Role.UMUM})
	public Long getCountDaftarPerusahaan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return personService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
