package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPersonRepository extends IRepository<Person> {
	DeleteResponse delete(String id);
	List<Person> getDaftarPerson(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
