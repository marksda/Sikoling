package org.Sikoling.main.restful.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.service.file.IStorageService;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;
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
	private IStorageService storageService;	
	
	@POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PersonDTO save(@FormDataParam("personData") String personData, 
			@FormDataParam("imageKtp") File imageKtp) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		PersonDTO personDTO = jsonb.fromJson(personData, PersonDTO.class);
		String fileKey = UUID.randomUUID().toString() + "-" + personDTO.getNik();		
		personDTO.setScanKTP("/identitas_personal/".concat(fileKey));		
		Person person = personService.save(personDTO.toPerson());
		
		if(person != null) {			
			try {
				InputStream uploadedInputStream = new FileInputStream(imageKtp);
				String subPathLocation = File.separator.concat("identitas_personal");
				boolean statusSaveFile = storageService.save(fileKey, uploadedInputStream, subPathLocation);
				if(statusSaveFile == true) {
					return new PersonDTO(person);
				}
				else {
					throw new IOException("gambar tidak bisa disimpan");
				}
			} catch (IOException e) {
				throw new IOException("gambar tidak bisa disimpan");
			}
			
		}
		
		throw new IOException("data sudah ada");
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PersonDTO update(PersonDTO personDTO) {
		return new PersonDTO(personService.update(personDTO.toPerson()));
	}
		
	@Path("id/{idLama}")
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PersonDTO updateId(@PathParam("idLama") String idLama, PersonDTO d) throws IOException {
		return new PersonDTO(personService.updateId(idLama, d.toPerson()));
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PersonDTO delete(PersonDTO d) throws IOException {
		return new PersonDTO(personService.delete(d.toPerson()));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
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
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public Long getCountDaftarPerusahaan(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return personService.getJumlahData(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
