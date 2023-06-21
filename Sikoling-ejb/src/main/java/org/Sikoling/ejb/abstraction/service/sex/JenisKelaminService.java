package org.Sikoling.ejb.abstraction.service.sex;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;

public class JenisKelaminService implements IJenisKelaminService {
	
	private final IJenisKelaminRepository jenisKelaminRepository;

	public JenisKelaminService(IJenisKelaminRepository jenisKelaminRepository) {
		this.jenisKelaminRepository = jenisKelaminRepository;
	}

	@Override
	public JenisKelamin save(JenisKelamin t) throws IOException {
		return jenisKelaminRepository.save(t);
	}

	@Override
	public JenisKelamin update(JenisKelamin t) {
		return jenisKelaminRepository.update(t);
	}

	@Override
	public JenisKelamin updateId(String idLama, JenisKelamin t) throws IOException {
		return jenisKelaminRepository.updateId(idLama, t);
	}

	@Override
	public JenisKelamin delete(JenisKelamin t) throws IOException {
		return jenisKelaminRepository.delete(t);
	}

	@Override
	public List<JenisKelamin> getDaftarData(QueryParamFilters queryParamFilters) {
		return jenisKelaminRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return jenisKelaminRepository.getJumlahData(queryParamFilters);
	}
	
}
