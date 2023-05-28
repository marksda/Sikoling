package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public interface IStatusFlowLogRepository extends IRepository<StatusFlowLog> {
	StatusFlowLog updateById(String id, StatusFlowLog statusFlowLog);
	DeleteResponse delete(String id);
	List<StatusFlowLog> getDaftarStatusFlowLog(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
