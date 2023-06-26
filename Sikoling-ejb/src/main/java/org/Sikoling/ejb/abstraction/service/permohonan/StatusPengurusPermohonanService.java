package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;

public class StatusPengurusPermohonanService implements IStatusPengurusPermohonanService {
	
	private final IStatusPengurusPermohonanRepository statusPengurusPermohonanRepository;	

	public StatusPengurusPermohonanService(IStatusPengurusPermohonanRepository statusPengurusPermohonanRepository) {
		this.statusPengurusPermohonanRepository = statusPengurusPermohonanRepository;
	}

	@Override
	public StatuswaliPermohonan save(StatuswaliPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.save(t);
	}

	@Override
	public StatuswaliPermohonan update(StatuswaliPermohonan t) {
		return statusPengurusPermohonanRepository.update(t);
	}

	@Override
	public StatuswaliPermohonan updateId(String idLama, StatuswaliPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.updateId(idLama, t);
	}

	@Override
	public StatuswaliPermohonan delete(StatuswaliPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.delete(t);
	}

	@Override
	public List<StatuswaliPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
		return statusPengurusPermohonanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return statusPengurusPermohonanRepository.getJumlahData(queryParamFilters);
	}
	
}
