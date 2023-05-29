package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;

public class StatusPengurusPermohonanService implements IStatusPengurusPermohonanService {
	
	private final IStatusPengurusPermohonanRepository statusWaliRepository;	

	public StatusPengurusPermohonanService(IStatusPengurusPermohonanRepository statusWaliRepository) {
		this.statusWaliRepository = statusWaliRepository;
	}

	@Override
	public DeleteResponse delete(String id) {
		return statusWaliRepository.delete(id);
	}

	@Override
	public StatusPengurusPermohonan save(StatusPengurusPermohonan statusWali) {
		return statusWaliRepository.save(statusWali);
	}

	@Override
	public StatusPengurusPermohonan update(StatusPengurusPermohonan statusWali) {
		return statusWaliRepository.update(statusWali);
	}

	
	@Override
	public List<StatusPengurusPermohonan> getDaftarStatusPengurusPermohonan(QueryParamFilters queryParamFilters) {
		return statusWaliRepository.getDaftarStatusPengurusPermohonan(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return statusWaliRepository.getCount(queryParamFilters);
	}

	@Override
	public StatusPengurusPermohonan updateById(String id, StatusPengurusPermohonan statusPengurusPermohonan) {
		return statusWaliRepository.updateById(id, statusPengurusPermohonan);
	}
	
}
