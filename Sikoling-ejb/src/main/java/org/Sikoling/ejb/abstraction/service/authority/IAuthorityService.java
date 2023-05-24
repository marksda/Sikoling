package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAuthorityService {
	Authority save(Authority t);
	Authority update(Authority t);
	DeleteResponse delete(String id);
	Authority getByUserName(String userName);
	List<Authority> getDaftarAuthority(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}
