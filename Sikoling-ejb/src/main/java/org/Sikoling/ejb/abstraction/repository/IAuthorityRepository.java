package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAuthorityRepository extends IRepository<Authority> {	
	DeleteResponse delete(String id);
	Authority getByUserName(String userName);
	List<Authority> getDaftarAuthority(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}
