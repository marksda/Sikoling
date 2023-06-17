package org.Sikoling.ejb.abstraction.service.jabatan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IJabatanService {
	Jabatan save(Jabatan t) throws IOException;
	Jabatan update(Jabatan t);
	Jabatan updateId(String idLama, Jabatan t) throws IOException;
	Jabatan delete(Jabatan t) throws IOException;
	List<Jabatan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
