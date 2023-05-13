package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;

public interface IFlowLogRepository extends IRepository<FlowLog> {
	DeleteResponse delete(String id);
	List<FlowLog> getDaftarFlowLog(QueryParamFilters queryParamFilters);
//	List<FlowLog> getAllByPage(Integer page, Integer pageSize);
//	List<FlowLog> getByIdPengakses(String idPengakses);
//	List<FlowLog> getByIdKategori(String idKategoriLog);
}
