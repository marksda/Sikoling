package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPegawaiPerusahaanRepository extends IRepository<Pegawai> {
	Pegawai updateById(String id, Pegawai pegawai);
	DeleteResponse delete(String id);
	List<Pegawai> getDaftarPegawai(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
