package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class AutorityPerusahaanRepositoryEJB implements IAutorityPerusahaanRepository {
	
	@Inject
	private AutorityPerusahaanRepositoryJPA autorityPerusahaanRepository;

	@Override
	public List<AutorityPerusahaan> getAll() {
		return autorityPerusahaanRepository.getAll();
	}

	@Override
	public AutorityPerusahaan save(AutorityPerusahaan t) {
		return autorityPerusahaanRepository.save(t);
	}

	@Override
	public AutorityPerusahaan update(AutorityPerusahaan t) {
		return autorityPerusahaanRepository.update(t);
	}

	@Override
	public List<AutorityPerusahaan> getDaftarAutorityPerusahaan(QueryParamFilters queryParamFilters) {
		return autorityPerusahaanRepository.getDaftarAutorityPerusahaan(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return autorityPerusahaanRepository.getCount(queryParamFilters);
	}

	@Override
	public DeleteResponse delete(String idAutority, String idRegisterPerusahaan) {
		return autorityPerusahaanRepository.delete(idAutority, idRegisterPerusahaan);
	}

	@Override
	public AutorityPerusahaan updateById(String idLamaAutority, String idLamaRegisterPerusahaan,
			AutorityPerusahaan dataBaru) {
		return autorityPerusahaanRepository.updateById(idLamaAutority, idLamaRegisterPerusahaan, dataBaru);
	}
}
