package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;

public interface IKbliRepository extends IRepository<Kbli2020> {
	DeleteResponse delete(String kode);
	Kbli2020 updateById(String id, Kbli2020 kbli);
	List<Kbli2020> getDaftarKbli2020(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
