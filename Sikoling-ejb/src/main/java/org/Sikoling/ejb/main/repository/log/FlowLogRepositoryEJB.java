package org.Sikoling.ejb.main.repository.log;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class FlowLogRepositoryEJB implements IFlowLogRepository {

	@Inject
	private FlowLogRepositoryJPA flowLogRepository;

	@Override
	public FlowLog save(FlowLog t) throws IOException {
		return flowLogRepository.save(t);
	}

	@Override
	public FlowLog update(FlowLog t) {
		return flowLogRepository.update(t);
	}

	@Override
	public FlowLog delete(FlowLog t) throws IOException {
		return flowLogRepository.delete(t);
	}

	@Override
	public List<FlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		return flowLogRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return flowLogRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public FlowLog updateId(String idLama, FlowLog t) throws IOException {
		return flowLogRepository.updateId(idLama, t);
	}
		
}
