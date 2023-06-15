package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;
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
	public List<RegisterPerusahaan> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerPerusahaanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public RegisterPerusahaan deleteLinkKepemilikanPerusahaan(AutorityPerusahaan autorityPerusahaan)
			throws IOException {
		return registerPerusahaanRepository.deleteLinkKepemilikanPerusahaan(autorityPerusahaan);
	}

	@Override
	public RegisterPerusahaan addLinkKepemilanPerusahaan(AutorityPerusahaan autorityPerusahaan) throws IOException {
		return registerPerusahaanRepository.addLinkKepemilanPerusahaan(autorityPerusahaan);
	}
	
}
