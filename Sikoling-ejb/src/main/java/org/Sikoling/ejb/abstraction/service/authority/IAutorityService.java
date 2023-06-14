package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityService {
	Autority save(Autority t);
	Autority update(Autority t);
	DeleteResponse delete(String id);
	Autority getByUserName(String userName);
	List<Autority> getDaftarAuthority(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}
