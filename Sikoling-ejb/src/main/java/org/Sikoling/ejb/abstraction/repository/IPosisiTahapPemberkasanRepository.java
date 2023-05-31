package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public interface IPosisiTahapPemberkasanRepository extends IRepository<PosisiTahapPemberkasan> {
	PosisiTahapPemberkasan updateById(String id, PosisiTahapPemberkasan posisiTahapPemberkasan);
	DeleteResponse delete(String id);
	List<PosisiTahapPemberkasan> getDaftarPosisiTahapPemberkasan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
