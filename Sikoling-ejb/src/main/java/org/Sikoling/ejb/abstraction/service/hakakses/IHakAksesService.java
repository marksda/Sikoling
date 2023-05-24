package org.Sikoling.ejb.abstraction.service.hakakses;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IHakAksesService {
	HakAkses save(HakAkses t);
	HakAkses update(HakAkses t);
	DeleteResponse delete(String id);
	List<HakAkses> getDaftarHakAkses(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}
