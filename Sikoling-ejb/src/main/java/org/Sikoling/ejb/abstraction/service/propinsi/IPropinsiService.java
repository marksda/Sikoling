package org.Sikoling.ejb.abstraction.service.propinsi;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPropinsiService {	
	Propinsi save(Propinsi t) throws IOException;
	Propinsi update(Propinsi t);
	Propinsi updateId(String idLama, Propinsi t) throws IOException;
	Propinsi delete(Propinsi t) throws IOException;
	List<Propinsi> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
