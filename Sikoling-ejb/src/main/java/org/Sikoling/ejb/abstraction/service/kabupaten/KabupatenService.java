package org.Sikoling.ejb.abstraction.service.kabupaten;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;

public class KabupatenService implements IKabupatenService {
	
	private final IKabupatenRepository kabupatenRepository;

	public KabupatenService(IKabupatenRepository kabupatenRepository) {
		this.kabupatenRepository = kabupatenRepository;
	}

	@Override
	public Kabupaten save(Kabupaten t) throws IOException {
		return kabupatenRepository.save(t);
	}

	@Override
	public Kabupaten update(Kabupaten t) {
		return kabupatenRepository.update(t);
	}

	@Override
	public Kabupaten updateId(String idLama, Kabupaten t) throws IOException {
		return kabupatenRepository.updateId(idLama, t);
	}

	@Override
	public Kabupaten delete(Kabupaten t) throws IOException {
		return kabupatenRepository.delete(t);
	}

	@Override
	public List<Kabupaten> getDaftarData(QueryParamFilters queryParamFilters) {
		return kabupatenRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kabupatenRepository.getJumlahData(queryParamFilters);
	}
	
}
