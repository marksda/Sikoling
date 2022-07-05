package org.Sikoling.ejb.abstraction.service.person;

import java.util.List;
import org.Sikoling.ejb.abstraction.entity.Person;

public interface IPersonService {
	Person save(Person person);
	Person update(Person person);
	List<Person> getAll();
	List<Person> getAllByPage(Integer page, Integer pageSize);
	List<Person> getByNama(String nama);
	List<Person> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
