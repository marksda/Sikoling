package org.Sikoling.ejb.abstraction.service.otoritas;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;

public class OtoritasService implements IOtoritasService {
	
	private final IAutorityRepository authorityRepository;

	public OtoritasService(IAutorityRepository authorityRepository) {
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
	
	@Override
	public Otoritas getById(String idOtoritas) {
		return authorityRepository.getById(idOtoritas);
	}
	
}
