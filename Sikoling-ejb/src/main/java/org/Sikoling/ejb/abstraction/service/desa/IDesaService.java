package org.Sikoling.ejb.abstraction.service.desa;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IDesaService {
	Desa save(Desa t) throws IOException;
	Desa update(Desa t);
	Desa updateId(String idLama, Desa t) throws IOException;
	Desa delete(Desa t) throws IOException;
	List<Desa> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
