package org.Sikoling.ejb.abstraction.service.person;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Person;
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
	public List<Person> getAllByPage(Integer page, Integer pageSize) {
		return personRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Person> getByNama(String nama) {
		return personRepository.getByNama(nama);
	}

	@Override
	public List<Person> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return personRepository.getByNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public Person getByNik(String nik) {
		return personRepository.getByNik(nik);
	}

}
