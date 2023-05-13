package org.Sikoling.ejb.main.repository.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class FlowLogRepositoryEJB implements IFlowLogRepository {

	@Inject
	private FlowLogRepositoryJPA flowLogRepository;
	
	@Override
	public List<FlowLog> getAll() {
		return flowLogRepository.getAll();
	}

	@Override
	public FlowLog save(FlowLog t) {
		return flowLogRepository.save(t);
	}

	@Override
	public FlowLog update(FlowLog t) {
		return flowLogRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return flowLogRepository.delete(id);
	}

	
	@Override
	public List<FlowLog> getDaftarFlowLog(QueryParamFilters queryParamFilters) {
		return flowLogRepository.getDaftarFlowLog(queryParamFilters);
	}

}
