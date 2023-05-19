package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class RegisterPermohonanRepositoryEJB implements IRegisterPermohonanRepository {

	@Inject
	private RegisterPermohonanRepositoryJPA registerPermohonanRepository;
	
	@Override
	public List<RegisterPermohonan> getAll() {
		return registerPermohonanRepository.getAll();
	}

	@Override
	public RegisterPermohonan save(RegisterPermohonan t) {
		return registerPermohonanRepository.save(t);
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		return registerPermohonanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return registerPermohonanRepository.delete(id);
	}
	
	@Override
	public List<RegisterPermohonan> getDaftarPermohonan(QueryParamFilters queryParamFilters) {		
		return registerPermohonanRepository.getDaftarPermohonan(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return registerPermohonanRepository.getCount(queryParamFilters);
	}

}
