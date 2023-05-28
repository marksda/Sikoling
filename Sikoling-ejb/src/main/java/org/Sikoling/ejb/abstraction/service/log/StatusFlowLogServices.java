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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusFlowLog update(StatusFlowLog statusFlowLog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusFlowLog updateById(String id, StatusFlowLog statusFlowLog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteResponse delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusFlowLog> getDaftarStatusFlowLog(QueryParamFilters queryParamFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		// TODO Auto-generated method stub
		return null;
	}

}
