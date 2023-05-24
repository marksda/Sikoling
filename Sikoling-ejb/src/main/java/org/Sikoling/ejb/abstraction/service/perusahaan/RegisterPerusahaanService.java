package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;

public class RegisterPerusahaanService implements IRegisterPerusahaanService {
	
	private final IRegisterPerusahaanRepository perusahaanRepository;

	public RegisterPerusahaanService(IRegisterPerusahaanRepository pemrakarsaRepository) {
		this.perusahaanRepository = pemrakarsaRepository;
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {
		return perusahaanRepository.save(t);
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		return perusahaanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return perusahaanRepository.delete(id);
	}

	@Override
	public List<RegisterPerusahaan> getAll() {
		return perusahaanRepository.getAll();
	}

	@Override
	public Boolean cekById(String id) {
		return perusahaanRepository.cekById(id);
	}
	
	@Override
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idPerson, String idPerusahaan) {
		return perusahaanRepository.deleteLinkKepemilikanPerusahaan(idPerson, idPerusahaan);
	}
	
	@Override
	public List<RegisterPerusahaan> getDaftarPerusahaan(QueryParamFilters queryParamFilters) {
		return perusahaanRepository.getDaftarPerusahaan(queryParamFilters);
	}
	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return perusahaanRepository.getCount(queryParamFilters);
	}
	
}
