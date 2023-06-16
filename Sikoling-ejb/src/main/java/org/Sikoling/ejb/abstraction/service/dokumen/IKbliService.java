package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;

public interface IKbliService {
	Kbli2020 save(Kbli2020 t) throws IOException;
	Kbli2020 update(Kbli2020 t);
	Kbli2020 updateId(String idLama, Kbli2020 t) throws IOException;
	Kbli2020 delete(Kbli2020 t) throws IOException;
	List<Kbli2020> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
