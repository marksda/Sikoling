package org.Sikoling.ejb.abstraction.service.log;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public interface IStatusFlowLogServices {
	StatusFlowLog save(StatusFlowLog t) throws IOException;
	StatusFlowLog update(StatusFlowLog t);
	StatusFlowLog updateId(String idLama, StatusFlowLog t) throws IOException;
	StatusFlowLog delete(StatusFlowLog t) throws IOException;
	List<StatusFlowLog> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
