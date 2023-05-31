package org.Sikoling.ejb.abstraction.service.jabatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IJabatanService {
	Jabatan save(Jabatan jabatan);
	Jabatan update(Jabatan jabatan);
	Jabatan updateById(String id, Jabatan jabatan);
	DeleteResponse delete(String id);
	List<Jabatan> getDaftarJabatan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
