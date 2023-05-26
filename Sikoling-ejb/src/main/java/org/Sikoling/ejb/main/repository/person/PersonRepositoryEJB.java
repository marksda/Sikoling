package org.Sikoling.ejb.main.repository.person;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	private PersonRepositoryJPA personRepositoryJPA;

	@Override
	public List<Person> getAll() {
		return personRepositoryJPA.getAll();
	}

	@Override
	public Person save(Person t) {
		return personRepositoryJPA.save(t);
	}

	@Override
	public Person update(Person t) {
		return personRepositoryJPA.update(t);
	}

	
	@Override
	public DeleteResponse delete(String id) {
		return personRepositoryJPA.delete(id);
	}
	

	@Override
	public List<Person> getDaftarPerson(QueryParamFilters queryParamFilters) {
		return personRepositoryJPA.getDaftarPerson(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return personRepositoryJPA.getCount(queryParamFilters);
	}


}
