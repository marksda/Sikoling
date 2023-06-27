package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IOtoritasPerusahaanRepository;

public class AutorityPerusahaanService implements IAutorityPerusahaanService {
	
	private final IOtoritasPerusahaanRepository autorityPerusahaanRepository;

	public AutorityPerusahaanService(IOtoritasPerusahaanRepository autorityPerusahaanRepository) {
		this.autorityPerusahaanRepository = autorityPerusahaanRepository;
	}

	@Override
	public OtoritasPerusahaan save(OtoritasPerusahaan t) throws IOException {
		return autorityPerusahaanRepository.save(t);
	}

	@Override
	public OtoritasPerusahaan update(OtoritasPerusahaan t) {
		return autorityPerusahaanRepository.update(t);
	}

	@Override
	public OtoritasPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, OtoritasPerusahaan t) throws IOException {
		return autorityPerusahaanRepository.updateId(idLamaAutority, idLamaRegisterPerusahaan, t);
	}

	@Override
	public OtoritasPerusahaan delete(OtoritasPerusahaan t) throws IOException {
		return autorityPerusahaanRepository.delete(t);
	}

	@Override
	public List<OtoritasPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		return autorityPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return autorityPerusahaanRepository.getJumlahData(queryParamFilters);
	}
	
}
