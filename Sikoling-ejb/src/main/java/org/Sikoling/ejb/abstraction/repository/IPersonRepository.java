package org.Sikoling.ejb.abstraction.repository;

import java.util.List;
import org.Sikoling.ejb.abstraction.entity.Person;

public interface IPersonRepository extends IRepository<Person> {
	List<Person> getAllByPage(Integer page, Integer pageSize);
	List<Person> getByNama(String nama);
	List<Person> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	Person getByNik(String nik);
}
