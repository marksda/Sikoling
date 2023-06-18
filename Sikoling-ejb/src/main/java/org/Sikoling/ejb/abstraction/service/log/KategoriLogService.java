package org.Sikoling.ejb.abstraction.service.log;

import java.io.IOException;
import java.util.List;

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
	public KategoriFlowLog save(KategoriFlowLog t) throws IOException {
		return kategoriLogRepository.save(t);
	}

	@Override
	public KategoriFlowLog update(KategoriFlowLog t) {
		return kategoriLogRepository.update(t);
	}

	@Override
	public KategoriFlowLog updateId(String idLama, KategoriFlowLog t) throws IOException {
		return kategoriLogRepository.updateId(idLama, t);
	}

	@Override
	public KategoriFlowLog delete(KategoriFlowLog t) throws IOException {
		return kategoriLogRepository.delete(t);
	}

	@Override
	public List<KategoriFlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriLogRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriLogRepository.getJumlahData(queryParamFilters);
	}
	
}
