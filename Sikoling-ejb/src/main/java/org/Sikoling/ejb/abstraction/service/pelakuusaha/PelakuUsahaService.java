package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;

public class PelakuUsahaService implements IPelakuUsahaServices {
	
	private final IPelakuUsahaRepository pelakuUsahaRepository;

	public PelakuUsahaService(IPelakuUsahaRepository pelakuUsahaRepository) {
		this.pelakuUsahaRepository = pelakuUsahaRepository;
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) throws IOException {
		return pelakuUsahaRepository.save(t);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		return pelakuUsahaRepository.update(t);
	}

	@Override
	public PelakuUsaha updateId(String idLama, PelakuUsaha t) throws IOException {
		return pelakuUsahaRepository.updateId(idLama, t);
	}

	@Override
	public PelakuUsaha delete(PelakuUsaha t) throws IOException {
		return pelakuUsahaRepository.delete(t);
	}

	@Override
	public List<PelakuUsaha> getDaftarData(QueryParamFilters queryParamFilters) {
		return pelakuUsahaRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return pelakuUsahaRepository.getJumlahData(queryParamFilters);
	}
	
}
