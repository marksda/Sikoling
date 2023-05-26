package org.Sikoling.main.restful.person;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.main.restful.queryparams.QueryParamFiltersDTO;
import org.Sikoling.main.restful.response.DeleteResponseDTO;
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
@Path("person")
public class PersonController {
	
	@Inject
	private IPersonService personService;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PersonDTO save(PersonDTO personDTO) {
		return new PersonDTO(personService.save(personDTO.toPerson()));
	}
	
	@PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public PersonDTO update(PersonDTO personDTO) {
		return new PersonDTO(personService.update(personDTO.toPerson()));
	}
	
//	@GET
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//	public List<PersonDTO> getAll() {
//		return personService.getAll()
//				.stream()
//				.map(t -> new PersonDTO(t))
//				.collect(Collectors.toList());
//	}
	
	@Path("{id}")
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public DeleteResponseDTO delete(@PathParam("id") String id) {
		return new DeleteResponseDTO(personService.delete(id));
	}
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public List<PersonDTO> getDaftarPerson(@Context UriInfo info) {
		MultivaluedMap<String, String> map = info.getQueryParameters();
		String queryParamsStr = map.getFirst("filters");
		Jsonb jsonb = JsonbBuilder.create();
		QueryParamFiltersDTO queryParamFiltersDTO = jsonb.fromJson(queryParamsStr, QueryParamFiltersDTO.class);
		
		return personService.getDaftarPerson(queryParamFiltersDTO.toQueryParamFilters())
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
		
		return personService.getCount(queryParamFiltersDTO.toQueryParamFilters().getFilters());
	}
	
}
