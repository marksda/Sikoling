package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;

public interface IKategoriLogService {
	List<KategoriFlowLog> getAll();
	DeleteResponse delete(String id);
	KategoriFlowLog save(KategoriFlowLog t);
	KategoriFlowLog update(KategoriFlowLog t);
	List<KategoriFlowLog> getDaftarKategoriLog(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
