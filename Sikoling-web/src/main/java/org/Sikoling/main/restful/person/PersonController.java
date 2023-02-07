package org.Sikoling.main.restful.person;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.main.restful.security.RequiredAuthorization;
import org.Sikoling.main.restful.security.RequiredRole;
import org.Sikoling.main.restful.security.Role;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

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
	
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PersonDTO> getAll() {
		return personService.getAll()
				.stream()
				.map(t -> new PersonDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PersonDTO> getAllByPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return personService.getAllByPage(page, pageSize)
				.stream()
				.map(t -> new PersonDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PersonDTO> getByQueryNama(@QueryParam("nama") String nama) {
		return personService.getByNama(nama)
				.stream()
				.map(t -> new PersonDTO(t))
				.collect(Collectors.toList());
	}
	
	@Path("nama/page")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public List<PersonDTO> getByQueryNamaAndPage(@QueryParam("nama") String nama, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
		return personService.getByNamaAndPage(nama, page, pageSize)
				.stream()
				.map(t -> new PersonDTO(t))
				.collect(Collectors.toList());
	}

	@Path("nik/{noNik}")
	@GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	@RequiredAuthorization
	@RequiredRole({Role.ADMIN, Role.PEMRAKARSA})
	public PersonDTO getByNik(@PathParam("noNik") String noNik) {
		Person person = personService.getByNik(noNik);		
		return  person != null ? new PersonDTO(person) : null;
	}
}
