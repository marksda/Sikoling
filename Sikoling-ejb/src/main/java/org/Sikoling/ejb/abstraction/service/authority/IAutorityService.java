package org.Sikoling.ejb.abstraction.service.authority;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityService {
	Autority save(Autority t) throws IOException;
	Autority update(Autority t);
	Autority updateId(String idLama, Autority t) throws IOException;
	Autority delete(Autority t) throws IOException;
	Autority getByUserName(String userName);
	List<Autority> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);	
}
