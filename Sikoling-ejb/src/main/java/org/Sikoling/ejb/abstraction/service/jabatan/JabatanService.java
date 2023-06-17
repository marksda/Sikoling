package org.Sikoling.ejb.abstraction.service.jabatan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;

public class JabatanService implements IJabatanService {
	
	private final IJabatanRepository jabatanRepository;

	public JabatanService(IJabatanRepository jabatanRepository) {
		this.jabatanRepository = jabatanRepository;
	}

	@Override
	public Jabatan save(Jabatan t) throws IOException {
		return jabatanRepository.save(t);
	}

	@Override
	public Jabatan update(Jabatan t) {
		return jabatanRepository.update(t);
	}

	@Override
	public Jabatan updateId(String idLama, Jabatan t) throws IOException {
		return jabatanRepository.updateId(idLama, t);
	}

	@Override
	public Jabatan delete(Jabatan t) throws IOException {
		return jabatanRepository.delete(t);
	}

	@Override
	public List<Jabatan> getDaftarData(QueryParamFilters queryParamFilters) {
		return jabatanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return jabatanRepository.getJumlahData(queryParamFilters);
	}
	
}
