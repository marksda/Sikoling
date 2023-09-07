package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class RegisterDokumenRepositoryEJB implements IRegisterDokumenRepository {
	
	@Inject
	private RegisterDokumenRepositoryJPA RegisterDokumenRepository;

	@Override
	public RegisterDokumen save(RegisterDokumen t) throws IOException {
		return RegisterDokumenRepository.save(t);
	}

	@Override
	public RegisterDokumen update(RegisterDokumen t) {
		return RegisterDokumenRepository.update(t);
	}

	@Override
	public RegisterDokumen delete(RegisterDokumen t) throws IOException {
		return RegisterDokumenRepository.delete(t);
	}

	@Override
	public List<RegisterDokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		return RegisterDokumenRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return RegisterDokumenRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public RegisterDokumen updateId(String idLama, RegisterDokumen t) throws IOException {
		return RegisterDokumenRepository.updateId(idLama, t);
	}

	
	@Override
	public RegisterDokumen getById(String id) throws IOException {
		return RegisterDokumenRepository.getById(id);
	}

}
