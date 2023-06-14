package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityRepository extends IRepository<Autority> {	
	DeleteResponse delete(String id);
	Autority getByUserName(String userName);
	List<Autority> getDaftarAuthority(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}
