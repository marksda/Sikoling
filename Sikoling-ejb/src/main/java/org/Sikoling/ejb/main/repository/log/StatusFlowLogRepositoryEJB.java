package org.Sikoling.ejb.main.repository.log;

import java.io.IOException;
import java.util.List;

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
	public StatusFlowLog save(StatusFlowLog t) throws IOException {
		return statusFlowLogRepository.save(t);
	}

	@Override
	public StatusFlowLog update(StatusFlowLog t) {
		return statusFlowLogRepository.update(t);
	}

	@Override
	public StatusFlowLog delete(StatusFlowLog t) throws IOException {
		return statusFlowLogRepository.delete(t);
	}

	@Override
	public List<StatusFlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		return statusFlowLogRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return statusFlowLogRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public StatusFlowLog updateId(String idLama, StatusFlowLog t) throws IOException {
		return statusFlowLogRepository.updateId(idLama, t);
	}

	
}
