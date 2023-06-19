package org.Sikoling.ejb.abstraction.service.person;

import java.io.IOException;
import java.util.List;

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
	public Person save(Person t) throws IOException {
		return personRepository.save(t);
	}

	@Override
	public Person update(Person t) {
		return personRepository.update(t);
	}

	@Override
	public Person updateId(String idLama, Person t) throws IOException {
		return personRepository.updateId(idLama, t);
	}

	@Override
	public Person delete(Person t) throws IOException {
		return personRepository.delete(t);
	}

	@Override
	public List<Person> getDaftarData(QueryParamFilters queryParamFilters) {
		return personRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return personRepository.getJumlahData(queryParamFilters);
	}

	
	
}
