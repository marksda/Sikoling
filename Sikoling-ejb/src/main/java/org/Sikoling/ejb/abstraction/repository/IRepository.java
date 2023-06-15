package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IRepository<T> {
	T save(T t) throws IOException;
	T update(T t);
	T delete(T t) throws IOException;
	List<T> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}