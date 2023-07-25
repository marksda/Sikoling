package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IDokumenRepository;

public class DokumenService implements IDokumenService {
	
	private final IDokumenRepository dokumenRepository;
	
	public DokumenService(IDokumenRepository dokumenRepository) {
		this.dokumenRepository = dokumenRepository;
	}

	@Override
	public Dokumen save(Dokumen t) throws IOException {
		return dokumenRepository.save(t);
	}

	@Override
	public Dokumen update(Dokumen t) {
		return dokumenRepository.update(t);
	}

	@Override
	public Dokumen updateId(String idLama, Dokumen t) throws IOException {
		return dokumenRepository.updateId(idLama, t);
	}

	@Override
	public Dokumen delete(Dokumen t) throws IOException {
		return dokumenRepository.delete(t);
	}

	@Override
	public List<Dokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		return dokumenRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return dokumenRepository.getJumlahData(queryParamFilters);
	}
	
}
