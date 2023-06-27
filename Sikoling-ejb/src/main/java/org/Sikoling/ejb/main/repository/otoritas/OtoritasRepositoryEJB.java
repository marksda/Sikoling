package org.Sikoling.ejb.main.repository.otoritas;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
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
public class OtoritasRepositoryEJB implements IAutorityRepository {
	
	@Inject
	private OtoritasRepositoryJPA autorisasiRepository;

	@Override
	public Otoritas save(Otoritas t) throws IOException {
		return autorisasiRepository.save(t);
	}

	@Override
	public Otoritas update(Otoritas t) {
		return autorisasiRepository.update(t);
	}

	@Override
	public Otoritas delete(Otoritas t) throws IOException {
		return autorisasiRepository.delete(t);
	}

	@Override
	public List<Otoritas> getDaftarData(QueryParamFilters queryParamFilters) {
		return autorisasiRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return autorisasiRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Otoritas updateId(String idLama, Otoritas t) throws IOException {
		return autorisasiRepository.updateId(idLama, t);
	}

	@Override
	public Otoritas getByUserName(String userName) {
		return autorisasiRepository.getByUserName(userName);
	}
	
}