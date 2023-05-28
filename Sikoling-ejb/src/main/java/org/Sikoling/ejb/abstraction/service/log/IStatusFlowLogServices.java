package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public interface IStatusFlowLogServices {
	StatusFlowLog save(StatusFlowLog statusFlowLog);
	StatusFlowLog update(StatusFlowLog statusFlowLog);
	StatusFlowLog updateById(String id, StatusFlowLog statusFlowLog);
	DeleteResponse delete(String id);
	List<StatusFlowLog> getDaftarStatusFlowLog(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
