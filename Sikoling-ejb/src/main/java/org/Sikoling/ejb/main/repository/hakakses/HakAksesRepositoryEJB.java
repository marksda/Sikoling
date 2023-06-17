package org.Sikoling.ejb.main.repository.hakakses;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IHakAksesRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class HakAksesRepositoryEJB implements IHakAksesRepository {
	
	@Inject
	private HakAksesRepositoryJPA hakAksesRepository;

	@Override
	public HakAkses save(HakAkses t) throws IOException {
		return hakAksesRepository.save(t);
	}

	@Override
	public HakAkses update(HakAkses t) {
		return hakAksesRepository.update(t);
	}

	@Override
	public HakAkses delete(HakAkses t) throws IOException {
		return hakAksesRepository.delete(t);
	}

	@Override
	public List<HakAkses> getDaftarData(QueryParamFilters queryParamFilters) {
		return hakAksesRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return hakAksesRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public HakAkses updateId(String idLama, HakAkses t) throws IOException {
		return hakAksesRepository.updateId(idLama, t);
	}
	
}
