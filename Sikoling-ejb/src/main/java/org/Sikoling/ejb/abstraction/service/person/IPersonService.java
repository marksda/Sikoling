package org.Sikoling.ejb.abstraction.service.person;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPersonService {
	Person save(Person t) throws IOException;
	Person update(Person t);
	Person updateId(String idLama, Person t) throws IOException;
	Person delete(Person t) throws IOException;
	List<Person> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
