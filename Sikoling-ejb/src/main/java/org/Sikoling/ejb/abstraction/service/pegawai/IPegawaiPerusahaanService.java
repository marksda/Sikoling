package org.Sikoling.ejb.abstraction.service.pegawai;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPegawaiPerusahaanService {
	Pegawai save(Pegawai pegawai);
	Pegawai update(Pegawai pegawai);
	Pegawai updateById(String id, Pegawai pegawai);
	DeleteResponse delete(String id);
	List<Pegawai> getDaftarPegawai(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
