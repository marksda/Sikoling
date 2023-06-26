package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;

public interface IKbliService {
	Kbli save(Kbli t) throws IOException;
	Kbli update(Kbli t);
	Kbli updateId(String idLama, Kbli t) throws IOException;
	Kbli delete(Kbli t) throws IOException;
	List<Kbli> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
