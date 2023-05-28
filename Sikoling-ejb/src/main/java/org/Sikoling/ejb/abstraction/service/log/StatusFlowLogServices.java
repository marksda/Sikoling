package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;
import org.Sikoling.ejb.abstraction.repository.IStatusFlowLogRepository;

public class StatusFlowLogServices implements IStatusFlowLogServices {
	
	private final IStatusFlowLogRepository statusFlowLogRepository;

	public StatusFlowLogServices(IStatusFlowLogRepository statusFlowLogRepository) {
		this.statusFlowLogRepository = statusFlowLogRepository;
	}

	@Override
	public StatusFlowLog save(StatusFlowLog statusFlowLog) {
		return statusFlowLogRepository.save(statusFlowLog);
	}

	@Override
	public StatusFlowLog update(StatusFlowLog statusFlowLog) {
		return statusFlowLogRepository.update(statusFlowLog);
	}

	@Override
	public StatusFlowLog updateById(String id, StatusFlowLog statusFlowLog) {
		return statusFlowLogRepository.updateById(id, statusFlowLog);
	}

	@Override
	public DeleteResponse delete(String id) {
		return statusFlowLogRepository.delete(id);
	}

	@Override
	public List<StatusFlowLog> getDaftarStatusFlowLog(QueryParamFilters queryParamFilters) {
		return statusFlowLogRepository.getDaftarStatusFlowLog(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return statusFlowLogRepository.getCount(queryParamFilters);
	}

}
