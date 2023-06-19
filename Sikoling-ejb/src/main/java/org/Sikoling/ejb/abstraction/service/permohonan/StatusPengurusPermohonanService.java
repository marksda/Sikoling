package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;

public class StatusPengurusPermohonanService implements IStatusPengurusPermohonanService {
	
	private final IStatusPengurusPermohonanRepository statusPengurusPermohonanRepository;	

	public StatusPengurusPermohonanService(IStatusPengurusPermohonanRepository statusPengurusPermohonanRepository) {
		this.statusPengurusPermohonanRepository = statusPengurusPermohonanRepository;
	}

	@Override
	public StatusPengurusPermohonan save(StatusPengurusPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.save(t);
	}

	@Override
	public StatusPengurusPermohonan update(StatusPengurusPermohonan t) {
		return statusPengurusPermohonanRepository.update(t);
	}

	@Override
	public StatusPengurusPermohonan updateId(String idLama, StatusPengurusPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.updateId(idLama, t);
	}

	@Override
	public StatusPengurusPermohonan delete(StatusPengurusPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.delete(t);
	}

	@Override
	public List<StatusPengurusPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
		return statusPengurusPermohonanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return statusPengurusPermohonanRepository.getJumlahData(queryParamFilters);
	}
	
}
