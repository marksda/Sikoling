package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;

public interface IKategoriLogRepository extends IRepository<KategoriFlowLog> {
	DeleteResponse delete(String id);
	List<KategoriFlowLog> getDaftarKategoriLog(QueryParamFilters queryParamFilters);
}