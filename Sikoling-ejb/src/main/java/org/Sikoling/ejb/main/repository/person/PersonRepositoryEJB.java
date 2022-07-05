package org.Sikoling.ejb.main.repository.person;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Person;
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
	public List<Person> getAllByPage(Integer page, Integer pageSize) {
		return personRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Person> getByNama(String nama) {
		return personRepositoryJPA.getByNama(nama);
	}

	@Override
	public List<Person> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return personRepositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

}
