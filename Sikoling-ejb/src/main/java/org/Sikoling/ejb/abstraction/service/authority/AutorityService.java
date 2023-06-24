package org.Sikoling.ejb.abstraction.service.authority;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;

public class AutorityService implements IAutorityService {
	
	private final IAutorityRepository authorityRepository;

	public AutorityService(IAutorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Otoritas save(Otoritas t) throws IOException {
		return authorityRepository.save(t);
	}

	@Override
	public Otoritas update(Otoritas t) {
		return authorityRepository.update(t);
	}

	@Override
	public Otoritas updateId(String idLama, Otoritas t) throws IOException {
		return authorityRepository.updateId(idLama, t);
	}

	@Override
	public Otoritas delete(Otoritas t) throws IOException {
		return authorityRepository.delete(t);
	}

	@Override
	public List<Otoritas> getDaftarData(QueryParamFilters queryParamFilters) {
		return authorityRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return authorityRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Otoritas getByUserName(String userName) {
		return authorityRepository.getByUserName(userName);
	}
	
}
