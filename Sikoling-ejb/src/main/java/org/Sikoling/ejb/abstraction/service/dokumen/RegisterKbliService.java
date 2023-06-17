package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;

public class RegisterKbliService implements IRegisterKbliService {
	
	private final IRegisterKbliRepository registerKbliRepository;

	public RegisterKbliService(IRegisterKbliRepository registerKbliRepository) {
		this.registerKbliRepository = registerKbliRepository;
	}

	@Override
	public RegisterKbli save(RegisterKbli t) throws IOException {
		return registerKbliRepository.save(t);
	}

	@Override
	public RegisterKbli update(RegisterKbli t) {
		return registerKbliRepository.update(t);
	}

	@Override
	public RegisterKbli updateId(String idNibLama, String idKbliLama, RegisterKbli t) throws IOException {
		return registerKbliRepository.updateId(idNibLama, idKbliLama, t);
	}

	@Override
	public RegisterKbli delete(RegisterKbli t) throws IOException {
		return registerKbliRepository.delete(t);
	}

	@Override
	public List<RegisterKbli> getDaftarData(QueryParamFilters queryParamFilters) {
		return registerKbliRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return registerKbliRepository.getJumlahData(queryParamFilters);
	}
	
	
}
