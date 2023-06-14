package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;

public class AutorityService implements IAutorityService {
	
	private final IAutorityRepository authorityRepository;

	public AutorityService(IAutorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Autority save(Autority t) {
		return authorityRepository.save(t);
	}

	@Override
	public Autority update(Autority t) {
		return authorityRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return authorityRepository.delete(id);
	}

	@Override
	public Autority getByUserName(String userName) {
		return authorityRepository.getByUserName(userName);
	}

	@Override
	public List<Autority> getDaftarAuthority(QueryParamFilters queryParamFilters) {
		return authorityRepository.getDaftarAuthority(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return authorityRepository.getCount(queryParamFilters);
	}
	
}
