package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	public DeleteResponse delete(String id) {
		return statusPengurusPermohonanRepository.delete(id);
	}

	@Override
	public StatusPengurusPermohonan save(StatusPengurusPermohonan statusPengurusPermohonan) {
		return statusPengurusPermohonanRepository.save(statusPengurusPermohonan);
	}

	@Override
	public StatusPengurusPermohonan update(StatusPengurusPermohonan statusPengurusPermohonan) {
		return statusPengurusPermohonanRepository.update(statusPengurusPermohonan);
	}

	
	@Override
	public List<StatusPengurusPermohonan> getDaftarStatusPengurusPermohonan(QueryParamFilters queryParamFilters) {
		return statusPengurusPermohonanRepository.getDaftarStatusPengurusPermohonan(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return statusPengurusPermohonanRepository.getCount(queryParamFilters);
	}

	@Override
	public StatusPengurusPermohonan updateById(String id, StatusPengurusPermohonan statusPengurusPermohonan) {
		return statusPengurusPermohonanRepository.updateById(id, statusPengurusPermohonan);
	}
	
}
