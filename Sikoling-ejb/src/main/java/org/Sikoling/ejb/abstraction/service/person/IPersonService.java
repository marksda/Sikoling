package org.Sikoling.ejb.abstraction.service.person;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPersonService {
	Person save(Person person);
	Person update(Person person);
	List<Person> getAll();
//	List<Person> getAllByPage(Integer page, Integer pageSize);
//	List<Person> getByNama(String nama);
//	List<Person> getByNamaAndPage(String nama, Integer page, Integer pageSize);
//	Person getByNik(String nik);
	DeleteResponse delete(String id);
	List<Person> getDaftarPerson(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
