package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
import org.Sikoling.ejb.abstraction.entity.Filter;
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
	public RegisterPerusahaan delete(RegisterPerusahaan t) throws IOException {
		return registerPerusahaanRepository.delete(t);
	}

	@Override
	public RegisterPerusahaan deleteLinkKepemilikanPerusahaan(AutorityPerusahaan autorityPerusahaan) throws IOException {
		return registerPerusahaanRepository.deleteLinkKepemilikanPerusahaan(autorityPerusahaan);
	}

	@Override
	public RegisterPerusahaan addLinkKepemilanPerusahaan(AutorityPerusahaan autorityPerusahaan) throws IOException {
		return registerPerusahaanRepository.addLinkKepemilanPerusahaan(autorityPerusahaan);
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
