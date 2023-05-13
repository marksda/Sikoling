package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;

public interface IFlowLogService {
	DeleteResponse delete(String id);
	FlowLog save(FlowLog t);
	FlowLog update(FlowLog t);
	List<FlowLog> getDaftarFlowLog(QueryParamFilters queryParamFilters);
}
