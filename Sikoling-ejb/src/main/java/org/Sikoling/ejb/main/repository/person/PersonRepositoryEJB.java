package org.Sikoling.ejb.main.repository.person;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PersonRepositoryEJB implements IPersonRepository {
	
	@Inject
	private PersonRepositoryJPA personRepository;

	@Override
	public Person save(Person t) throws IOException {
		return personRepository.save(t);
	}

	@Override
	public Person update(Person t) {
		return personRepository.update(t);
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

	@Override
	public Person updateId(String idLama, Person t) throws IOException {
		return personRepository.updateId(idLama, t);
	}

	
}
