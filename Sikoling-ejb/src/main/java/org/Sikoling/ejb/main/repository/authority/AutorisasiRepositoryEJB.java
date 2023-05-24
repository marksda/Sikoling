package org.Sikoling.ejb.main.repository.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class AutorisasiRepositoryEJB implements IAuthorityRepository {
	
	@Inject
	private AutorisasiRepositoryJPA autorisasiRepository;

	@Override
	public List<Authority> getAll() {
		return autorisasiRepository.getAll();
	}

	@Override
	public Authority save(Authority t) {
		return autorisasiRepository.save(t);
	}

	@Override
	public Authority update(Authority t) {
		return autorisasiRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return autorisasiRepository.delete(id);
	}
	
	@Override
	public Authority getByUserName(String userName) {
		return autorisasiRepository.getByUserName(userName);
	}

	
	@Override
	public List<Authority> getDaftarAuthority(QueryParamFilters queryParamFilters) {
		return autorisasiRepository.getDaftarAuthority(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return autorisasiRepository.getCount(queryParamFilters);
	}
	
}