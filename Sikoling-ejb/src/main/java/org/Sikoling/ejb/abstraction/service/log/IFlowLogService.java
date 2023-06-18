package org.Sikoling.ejb.abstraction.service.log;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;

public interface IFlowLogService {
	FlowLog save(FlowLog t) throws IOException;
	FlowLog update(FlowLog t);
	FlowLog updateId(String idLama, FlowLog t) throws IOException;
	FlowLog delete(FlowLog t) throws IOException;
	List<FlowLog> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
