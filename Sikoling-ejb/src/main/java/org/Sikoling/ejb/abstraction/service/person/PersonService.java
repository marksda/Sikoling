package org.Sikoling.ejb.abstraction.service.person;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;

public class PersonService implements IPersonService {
	
	private final IPersonRepository personRepository;

	public PersonService(IPersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person update(Person person) {
		return personRepository.update(person);
	}

	@Override
	public List<Person> getAll() {
		return personRepository.getAll();
	}


	@Override
	public DeleteResponse delete(String id) {
		return personRepository.delete(id);
	}

	
	@Override
	public List<Person> getDaftarPerson(QueryParamFilters queryParamFilters) {
		return personRepository.getDaftarPerson(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return personRepository.getCount(queryParamFilters);
	}

}
