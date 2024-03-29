package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class RegisterPerusahaanRepositoryEJB implements IRegisterPerusahaanRepository {
	
	@Inject
	private RegisterPerusahaanRepositoryJPA registerPerusahaanRepository;

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) throws IOException {
		return registerPerusahaanRepository.save(t);
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		return registerPerusahaanRepository.update(t);
	}

	@Override
	public RegisterPerusahaan delete(RegisterPerusahaan t) throws IOException {
		return registerPerusahaanRepository.delete(t);
	}

	@Override
	public List<RegisterPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerPerusahaanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public RegisterPerusahaan updateId(String idLama, RegisterPerusahaan t) throws IOException {
		return registerPerusahaanRepository.updateId(idLama, t);
	}
	

	@Override
	public RegisterPerusahaan delete(RegisterPerusahaan t, Otoritas userOtoritas) throws IOException {
		return registerPerusahaanRepository.delete(t, userOtoritas);
	}

}
