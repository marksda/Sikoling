package org.Sikoling.ejb.abstraction.service.authority;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autority;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;

public class AutorityService implements IAutorityService {
	
	private final IAutorityRepository authorityRepository;

	public AutorityService(IAutorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Autority save(Autority t) throws IOException {
		return authorityRepository.save(t);
	}

	@Override
	public Autority update(Autority t) {
		return authorityRepository.update(t);
	}

	@Override
	public Autority updateId(String idLama, Autority t) throws IOException {
		return authorityRepository.updateId(idLama, t);
	}

	@Override
	public Autority delete(Autority t) throws IOException {
		return authorityRepository.delete(t);
	}

	@Override
	public List<Autority> getDaftarData(QueryParamFilters queryParamFilters) {
		return authorityRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return authorityRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Autority getByUserName(String userName) {
		return authorityRepository.getByUserName(userName);
	}
	
}
