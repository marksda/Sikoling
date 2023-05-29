package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.repository.IKategoriLogRepository;

public class KategoriLogService implements IKategoriLogService {
	
	private final IKategoriLogRepository kategoriLogRepository;

	public KategoriLogService(IKategoriLogRepository kategoriLogRepository) {
		this.kategoriLogRepository = kategoriLogRepository;
	}
	
	@Override
	public List<KategoriFlowLog> getAll() {
		return kategoriLogRepository.getAll();
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriLogRepository.delete(id);
	}

	@Override
	public KategoriFlowLog save(KategoriFlowLog t) {
		return kategoriLogRepository.save(t);
	}

	@Override
	public KategoriFlowLog update(KategoriFlowLog t) {
		return kategoriLogRepository.update(t);
	}

	@Override
	public List<KategoriFlowLog> getDaftarKategoriLog(QueryParamFilters queryParamFilters) {
		return kategoriLogRepository.getDaftarKategoriLog(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kategoriLogRepository.getCount(queryParamFilters);
	}

	
}
