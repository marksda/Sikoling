package org.Sikoling.ejb.abstraction.service.authority;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IAutorityService {
	Otoritas save(Otoritas t) throws IOException;
	Otoritas update(Otoritas t);
	Otoritas updateId(String idLama, Otoritas t) throws IOException;
	Otoritas delete(Otoritas t) throws IOException;
	Otoritas getByUserName(String userName);
	List<Otoritas> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);	
}
