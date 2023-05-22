package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class RegisterPerusahaanRepositoryEJB implements IRegisterPerusahaanRepository {
	
	@Inject
	private RegisterPerusahaanRepositoryJPA registerPerusahaanRepository;

	@Override
	public List<RegisterPerusahaan> getAll() {		
		return registerPerusahaanRepository.getAll();
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {
		return registerPerusahaanRepository.save(t);
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		return registerPerusahaanRepository.update(t);
	}
	
	@Override
	public Boolean cekById(String id) {
		return registerPerusahaanRepository.cekById(id);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		return registerPerusahaanRepository.delete(id);
	}
	
	@Override
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idPerson, String idPerusahaan) {
		return registerPerusahaanRepository.deleteLinkKepemilikanPerusahaan(idPerson, idPerusahaan);
	}
	
	@Override
	public List<RegisterPerusahaan> getDaftarPerusahaan(QueryParamFilters queryParamFilters) {
		return registerPerusahaanRepository.getDaftarPerusahaan(queryParamFilters);
	}
	

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return registerPerusahaanRepository.getCount(queryParamFilters);
	}
	
}
