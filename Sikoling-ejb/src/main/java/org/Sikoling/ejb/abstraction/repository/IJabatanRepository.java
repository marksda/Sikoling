package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IJabatanRepository extends IRepository<Jabatan> {
	Jabatan updateById(String id, Jabatan jabatan);
	DeleteResponse delete(String id);
	List<Jabatan> getDaftarJabatan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
