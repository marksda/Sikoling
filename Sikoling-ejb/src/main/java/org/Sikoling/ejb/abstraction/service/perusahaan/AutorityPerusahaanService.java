package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityPerusahaanRepository;

public class AutorityPerusahaanService implements IAutorityPerusahaanService {
	
	private final IAutorityPerusahaanRepository autorityPerusahaanRepository;

	public AutorityPerusahaanService(IAutorityPerusahaanRepository autorityPerusahaanRepository) {
		this.autorityPerusahaanRepository = autorityPerusahaanRepository;
	}

	@Override
	public AutorityPerusahaan save(AutorityPerusahaan t) throws IOException {
		return autorityPerusahaanRepository.save(t);
	}

	@Override
	public AutorityPerusahaan update(AutorityPerusahaan t) {
		return autorityPerusahaanRepository.update(t);
	}

	@Override
	public AutorityPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, AutorityPerusahaan t) throws IOException {
		return autorityPerusahaanRepository.updateId(idLamaAutority, idLamaRegisterPerusahaan, t);
	}

	@Override
	public AutorityPerusahaan delete(AutorityPerusahaan t) throws IOException {
		return autorityPerusahaanRepository.delete(t);
	}

	@Override
	public List<AutorityPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		return autorityPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return autorityPerusahaanRepository.getJumlahData(queryParamFilters);
	}
	
}
