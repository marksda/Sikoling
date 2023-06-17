package org.Sikoling.ejb.main.repository.authority;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class AutorisasiRepositoryEJB implements IAutorityRepository {
	
	@Inject
	private AutorisasiRepositoryJPA autorisasiRepository;

	@Override
	public Autority save(Autority t) throws IOException {
		return autorisasiRepository.save(t);
	}

	@Override
	public Autority update(Autority t) {
		return autorisasiRepository.update(t);
	}

	@Override
	public Autority delete(Autority t) throws IOException {
		return autorisasiRepository.delete(t);
	}

	@Override
	public List<Autority> getDaftarData(QueryParamFilters queryParamFilters) {
		return autorisasiRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return autorisasiRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Autority updateId(String idLama, Autority t) throws IOException {
		return autorisasiRepository.updateId(idLama, t);
	}

	@Override
	public Autority getByUserName(String userName) {
		return autorisasiRepository.getByUserName(userName);
	}
	
}