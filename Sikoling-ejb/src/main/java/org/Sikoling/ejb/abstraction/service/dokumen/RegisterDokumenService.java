package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;

public class RegisterDokumenService implements IRegisterDokumenService {
	
	private final IRegisterDokumenRepository registerDokumenRepository;
	
	public RegisterDokumenService(IRegisterDokumenRepository registerDokumenRepository) {
		this.registerDokumenRepository = registerDokumenRepository;
	}

	@Override
	public RegisterDokumen save(RegisterDokumen t) throws IOException {
		return registerDokumenRepository.save(t);
	}

	@Override
	public RegisterDokumen update(RegisterDokumen t) {
		return registerDokumenRepository.update(t);
	}

	@Override
	public RegisterDokumen updateId(String idLama, RegisterDokumen t) throws IOException {
		return registerDokumenRepository.updateId(idLama, t);
	}

	@Override
	public RegisterDokumen delete(RegisterDokumen t) throws IOException {
		return registerDokumenRepository.delete(t);
	}

	@Override
	public List<RegisterDokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerDokumenRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerDokumenRepository.getJumlahData(queryParamFilters);
	}
	
}
