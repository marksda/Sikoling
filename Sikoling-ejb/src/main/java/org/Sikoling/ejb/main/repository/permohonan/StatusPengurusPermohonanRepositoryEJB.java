package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class StatusPengurusPermohonanRepositoryEJB implements IStatusPengurusPermohonanRepository {
	
	@Inject
	private StatusPengurusPermohonanRepositoryJPA statusPengurusPermohonanRepository;
	
	@Override
	public List<StatusPengurusPermohonan> getAll() {
		return statusPengurusPermohonanRepository.getAll();
	}

	@Override
	public StatusPengurusPermohonan save(StatusPengurusPermohonan t) {
		return statusPengurusPermohonanRepository.save(t);
	}

	@Override
	public StatusPengurusPermohonan update(StatusPengurusPermohonan t) {
		return statusPengurusPermohonanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return statusPengurusPermohonanRepository.delete(id);
	}

	@Override
	public StatusPengurusPermohonan updateById(String id, StatusPengurusPermohonan statusPengurusPermohonan) {
		return statusPengurusPermohonanRepository.updateById(id, statusPengurusPermohonan);
	}
	

	@Override
	public List<StatusPengurusPermohonan> getDaftarStatusPengurusPermohonan(QueryParamFilters queryParamFilters) {
		return statusPengurusPermohonanRepository.getDaftarStatusPengurusPermohonan(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return statusPengurusPermohonanRepository.getCount(queryParamFilters);
	}

}
