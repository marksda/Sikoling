package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;

public class AuthorityService implements IAuthorityService {
	
	private final IAuthorityRepository authorityRepository;

	public AuthorityService(IAuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Authority save(Authority t) {
		return authorityRepository.save(t);
	}

	@Override
	public Authority update(Authority t) {
		return authorityRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return authorityRepository.delete(id);
	}

	@Override
	public Authority getByUserName(String userName) {
		return authorityRepository.getByUserName(userName);
	}

	@Override
	public List<Authority> getDaftarAuthority(QueryParamFilters queryParamFilters) {
		return authorityRepository.getDaftarAuthority(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return authorityRepository.getCount(queryParamFilters);
	}
	
}
