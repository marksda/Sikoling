package org.Sikoling.ejb.abstraction.service.otoritas;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IOtoritasPerusahaanRepository;

public class OtoritasPerusahaanService implements IOtoritasPerusahaanService {
	
	private final IOtoritasPerusahaanRepository otoritasPerusahaanRepository;

	public OtoritasPerusahaanService(IOtoritasPerusahaanRepository otoritasPerusahaanRepository) {
		this.otoritasPerusahaanRepository = otoritasPerusahaanRepository;
	}

	@Override
	public OtoritasPerusahaan save(OtoritasPerusahaan t) throws IOException {
		return otoritasPerusahaanRepository.save(t);
	}

	@Override
	public OtoritasPerusahaan update(OtoritasPerusahaan t) {
		return otoritasPerusahaanRepository.update(t);
	}

	@Override
	public OtoritasPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, OtoritasPerusahaan t) throws IOException {
		return otoritasPerusahaanRepository.updateId(idLamaAutority, idLamaRegisterPerusahaan, t);
	}

	@Override
	public OtoritasPerusahaan delete(OtoritasPerusahaan t) throws IOException {
		return otoritasPerusahaanRepository.delete(t);
	}

	@Override
	public List<OtoritasPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		return otoritasPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return otoritasPerusahaanRepository.getJumlahData(queryParamFilters);
	}
	
}
