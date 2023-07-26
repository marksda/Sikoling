package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterKbli;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class RegisterKbliRepositoryEJB implements IRegisterKbliRepository {

	@Inject
	private RegisterKbliRepositoryJPA registerKbliRepository;

	@Override
	public RegisterKbli save(RegisterKbli t) throws IOException {
		return registerKbliRepository.save(t);
	}

	@Override
	public RegisterKbli update(RegisterKbli t) {
		return registerKbliRepository.update(t);
	}

	@Override
	public RegisterKbli delete(RegisterKbli t) throws IOException {
		return registerKbliRepository.delete(t);
	}

	@Override
	public List<RegisterKbli> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerKbliRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerKbliRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public RegisterKbli updateId(String idNibLama, String idKbliLama, RegisterKbli t) throws IOException {
		return registerKbliRepository.updateId(idNibLama, idKbliLama, t);
	}

	
}
