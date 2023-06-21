package org.Sikoling.ejb.main.repository.propinsi;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class PropinsiRepositoryEJB implements IPropinsiRepository {
	
	@Inject
	private PropinsiRepositoryJPA propinsiRepository;

	@Override
	public Propinsi save(Propinsi t) throws IOException {
		return propinsiRepository.save(t);
	}

	@Override
	public Propinsi update(Propinsi t) {
		return propinsiRepository.update(t);
	}

	@Override
	public Propinsi delete(Propinsi t) throws IOException {
		return propinsiRepository.delete(t);
	}

	@Override
	public List<Propinsi> getDaftarData(QueryParamFilters queryParamFilters) {
		return propinsiRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return propinsiRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public Propinsi updateId(String idLama, Propinsi t) throws IOException {
		return propinsiRepository.updateId(idLama, t);
	}
	
}
