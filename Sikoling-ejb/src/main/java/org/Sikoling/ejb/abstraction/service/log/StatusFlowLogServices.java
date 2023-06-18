package org.Sikoling.ejb.abstraction.service.log;

import java.io.IOException;
import java.util.List;

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
	public StatusFlowLog save(StatusFlowLog t) throws IOException {
		return statusFlowLogRepository.save(t);
	}

	@Override
	public StatusFlowLog update(StatusFlowLog t) {
		return statusFlowLogRepository.update(t);
	}

	@Override
	public StatusFlowLog updateId(String idLama, StatusFlowLog t) throws IOException {
		return statusFlowLogRepository.updateId(idLama, t);
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
	
}
