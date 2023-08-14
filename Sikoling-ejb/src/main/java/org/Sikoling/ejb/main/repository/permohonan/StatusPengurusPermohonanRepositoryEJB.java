package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class StatusPengurusPermohonanRepositoryEJB implements IStatusPengurusPermohonanRepository {
	
	@Inject
	private StatusPengurusPermohonanRepositoryJPA statusPengurusPermohonanRepository;

	@Override
	public StatuswaliPermohonan save(StatuswaliPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.save(t);
	}

	@Override
	public StatuswaliPermohonan update(StatuswaliPermohonan t) {
		return statusPengurusPermohonanRepository.update(t);
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

	@Override
	public StatuswaliPermohonan updateId(String idLama, StatuswaliPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.updateId(idLama, t);
	}
	
	
	
}
