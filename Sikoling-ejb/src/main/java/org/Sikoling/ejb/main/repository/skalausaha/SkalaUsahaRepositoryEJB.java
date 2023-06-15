package org.Sikoling.ejb.main.repository.skalausaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class SkalaUsahaRepositoryEJB implements ISkalaUsahaRepository {

	@Inject
	private SkalaUsahaRepositoryJPA skalaUsahaRepository;

	@Override
	public SkalaUsaha save(SkalaUsaha t) throws IOException {
		return skalaUsahaRepository.save(t);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha t) {
		return skalaUsahaRepository.update(t);
	}

	@Override
	public SkalaUsaha delete(SkalaUsaha t) throws IOException {
		return skalaUsahaRepository.delete(t);
	}

	@Override
	public List<SkalaUsaha> getDaftarData(QueryParamFilters queryParamFilters) {
		return skalaUsahaRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return skalaUsahaRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public SkalaUsaha updateId(String idLama, SkalaUsaha t) throws IOException {
		return skalaUsahaRepository.updateId(idLama, t);
	}
	
}
