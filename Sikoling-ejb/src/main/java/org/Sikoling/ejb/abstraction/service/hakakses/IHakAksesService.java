package org.Sikoling.ejb.abstraction.service.hakakses;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IHakAksesService {
	HakAkses save(HakAkses t) throws IOException;
	HakAkses update(HakAkses t);
	HakAkses updateId(String idLama, HakAkses t) throws IOException;
	HakAkses delete(HakAkses t) throws IOException;
	List<HakAkses> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);	
}
