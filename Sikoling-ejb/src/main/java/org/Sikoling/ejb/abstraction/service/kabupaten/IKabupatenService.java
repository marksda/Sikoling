package org.Sikoling.ejb.abstraction.service.kabupaten;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IKabupatenService {
	Kabupaten save(Kabupaten t) throws IOException;
	Kabupaten update(Kabupaten t);	
	Kabupaten updateId(String idLama, Kabupaten t) throws IOException;
	Kabupaten delete(Kabupaten t) throws IOException;
	List<Kabupaten> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
