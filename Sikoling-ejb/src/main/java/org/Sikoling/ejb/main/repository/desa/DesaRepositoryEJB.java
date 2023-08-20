package org.Sikoling.ejb.main.repository.desa;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DesaRepositoryEJB implements IDesaRepository {
	
	@Inject
	private DesaRepositoryJPA desaRepository;

	@Override
	public Desa save(Desa t) throws IOException {
		return desaRepository.save(t);
	}

	@Override
	public Desa update(Desa t) {
		return desaRepository.update(t);
	}

	@Override
	public Desa delete(Desa t) throws IOException {
		return desaRepository.delete(t);
	}

	@Override
	public List<Desa> getDaftarData(QueryParamFilters queryParamFilters) {
		return desaRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return desaRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Desa updateId(String idLama, Desa t) throws IOException {
		return desaRepository.updateId(idLama, t);
	}

}
