package org.Sikoling.ejb.main.repository.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;
import org.Sikoling.ejb.abstraction.repository.IStatusFlowLogRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class StatusFlowLogRepositoryEJB implements IStatusFlowLogRepository {
	
	@Inject
	private StatusFlowLogRepositoryJPA statusFlowLogRepository;

	@Override
	public List<StatusFlowLog> getAll() {
		return statusFlowLogRepository.getAll();
	}

	@Override
	public StatusFlowLog save(StatusFlowLog t) {
		return statusFlowLogRepository.save(t);
	}

	@Override
	public StatusFlowLog update(StatusFlowLog t) {
		return statusFlowLogRepository.update(t);
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
