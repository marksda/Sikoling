package org.Sikoling.ejb.main.repository.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	public List<Autority> getAll() {
		return autorisasiRepository.getAll();
	}

	@Override
	public Autority save(Autority t) {
		return autorisasiRepository.save(t);
	}

	@Override
	public Autority update(Autority t) {
		return autorisasiRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return autorisasiRepository.delete(id);
	}
	
	@Override
	public Autority getByUserName(String userName) {
		return autorisasiRepository.getByUserName(userName);
	}

	
	@Override
	public List<Autority> getDaftarAuthority(QueryParamFilters queryParamFilters) {
		return autorisasiRepository.getDaftarAuthority(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return autorisasiRepository.getCount(queryParamFilters);
	}
	
}