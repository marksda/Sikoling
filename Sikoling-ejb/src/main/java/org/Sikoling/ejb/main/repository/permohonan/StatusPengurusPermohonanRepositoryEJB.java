package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.List;

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
	public StatusPengurusPermohonan save(StatusPengurusPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.save(t);
	}

	@Override
	public StatusPengurusPermohonan update(StatusPengurusPermohonan t) {
		return statusPengurusPermohonanRepository.update(t);
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

	@Override
	public StatusPengurusPermohonan updateId(String idLama, StatusPengurusPermohonan t) throws IOException {
		return statusPengurusPermohonanRepository.updateId(idLama, t);
	}
	
	
	
}
