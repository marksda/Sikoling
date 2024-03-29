package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;

public class RegisterPerusahaanService implements IRegisterPerusahaanService {
	
	private final IRegisterPerusahaanRepository registerPerusahaanRepository;

	public RegisterPerusahaanService(IRegisterPerusahaanRepository pemrakarsaRepository) {
		this.registerPerusahaanRepository = pemrakarsaRepository;
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) throws IOException {
		return registerPerusahaanRepository.save(t);
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		return registerPerusahaanRepository.update(t);
	}

	@Override
	public RegisterPerusahaan updateId(String idLama, RegisterPerusahaan t) throws IOException {
		return registerPerusahaanRepository.updateId(idLama, t);
	}
	
	@Override
	public RegisterPerusahaan delete(RegisterPerusahaan t, Otoritas userOtoritas) throws IOException {
		return registerPerusahaanRepository.delete(t, userOtoritas);
	}

	@Override
	public List<RegisterPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerPerusahaanRepository.getJumlahData(queryParamFilters);
	}

	
}
