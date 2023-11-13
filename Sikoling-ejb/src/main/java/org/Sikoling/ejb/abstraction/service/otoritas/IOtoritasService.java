package org.Sikoling.ejb.abstraction.service.otoritas;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IOtoritasService {
	Otoritas save(Otoritas t) throws IOException;
	Otoritas update(Otoritas t);
	Otoritas updateId(String idLama, Otoritas t) throws IOException;
	Otoritas delete(Otoritas t) throws IOException;
	Otoritas getByUserName(String userName);
	Otoritas getById(String idOtoritas);
	List<Otoritas> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);	
}
