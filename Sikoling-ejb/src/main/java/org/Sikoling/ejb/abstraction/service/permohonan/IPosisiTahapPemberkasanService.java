package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public interface IPosisiTahapPemberkasanService {
	PosisiTahapPemberkasan updateById(String id, PosisiTahapPemberkasan posisiTahapPemberkasan);
	DeleteResponse delete(String id);
	PosisiTahapPemberkasan save(PosisiTahapPemberkasan t);
	PosisiTahapPemberkasan update(PosisiTahapPemberkasan t);
	List<PosisiTahapPemberkasan> getDaftarPosisiTahapPemberkasan(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
