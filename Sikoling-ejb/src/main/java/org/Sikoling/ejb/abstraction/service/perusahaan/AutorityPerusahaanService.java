package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IAutorityPerusahaanRepository;

public class AutorityPerusahaanService implements IAutorityPerusahaanService {
	
	private final IAutorityPerusahaanRepository autorityPerusahaanRepository;

	public AutorityPerusahaanService(IAutorityPerusahaanRepository autorityPerusahaanRepository) {
		this.autorityPerusahaanRepository = autorityPerusahaanRepository;
	}

	@Override
	public List<AutorityPerusahaan> getALL() {
		return autorityPerusahaanRepository.getAll();
	}

	@Override
	public AutorityPerusahaan save(AutorityPerusahaan autorityPerusahaan) {
		return autorityPerusahaanRepository.save(autorityPerusahaan);
	}

	@Override
	public AutorityPerusahaan update(AutorityPerusahaan autorityPerusahaan) {
		return autorityPerusahaanRepository.update(autorityPerusahaan);
	}

	@Override
	public DeleteResponse delete(String id) {
		return autorityPerusahaanRepository.delete(id);
	}

	@Override
	public List<AutorityPerusahaan> getDaftarAutorityPerusahaan(QueryParamFilters queryParamFilters) {
		return autorityPerusahaanRepository.getDaftarAutorityPerusahaan(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return autorityPerusahaanRepository.getCount(queryParamFilters);
	}

}
