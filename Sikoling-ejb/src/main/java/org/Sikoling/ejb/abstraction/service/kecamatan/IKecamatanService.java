package org.Sikoling.ejb.abstraction.service.kecamatan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IKecamatanService {
	Kecamatan save(Kecamatan t) throws IOException;
	Kecamatan update(Kecamatan t);
	Kecamatan updateId(String idLama, Kecamatan t) throws IOException;
	Kecamatan delete(Kecamatan t) throws IOException;
	List<Kecamatan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
