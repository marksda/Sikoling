package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;

public interface IKbliService {
	Kbli2020 save(Kbli2020 kbli);
	Kbli2020 update(Kbli2020 kbli);
	Kbli2020 updateById(String id, Kbli2020 kbli);
	DeleteResponse delete(String kode);
	List<Kbli2020> getDaftarKbli2020(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
