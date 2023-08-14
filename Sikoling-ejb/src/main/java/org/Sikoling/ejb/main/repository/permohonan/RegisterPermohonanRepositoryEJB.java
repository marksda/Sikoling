package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class RegisterPermohonanRepositoryEJB implements IRegisterPermohonanRepository {

	@Inject
	private RegisterPermohonanRepositoryJPA registerPermohonanRepository;

	@Override
	public RegisterPermohonan save(RegisterPermohonan t) throws IOException {
		return registerPermohonanRepository.save(t);
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		return registerPermohonanRepository.update(t);
	}

	@Override
	public RegisterPermohonan delete(RegisterPermohonan t) throws IOException {
		return registerPermohonanRepository.delete(t);
	}

	@Override
	public List<RegisterPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerPermohonanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerPermohonanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public RegisterPermohonan updateId(String idLama, RegisterPermohonan t) throws IOException {
		return registerPermohonanRepository.updateId(idLama, t);
	}
	
	
}
