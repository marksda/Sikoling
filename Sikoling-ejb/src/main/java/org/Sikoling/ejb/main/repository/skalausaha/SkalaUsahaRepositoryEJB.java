package org.Sikoling.ejb.main.repository.skalausaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	public List<SkalaUsaha> getAll() {
		return skalaUsahaRepository.getAll();
	}

	@Override
	public SkalaUsaha save(SkalaUsaha t) {
		return skalaUsahaRepository.save(t);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha t) {
		return skalaUsahaRepository.update(t);
	}

	@Override
	public SkalaUsaha updateById(String id, SkalaUsaha skalaUsaha) {
		return skalaUsahaRepository.updateById(id, skalaUsaha);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		return skalaUsahaRepository.delete(id);
	}

	
	@Override
	public List<SkalaUsaha> getDaftarSkalaUsaha(QueryParamFilters queryParamFilters) {
		return skalaUsahaRepository.getDaftarSkalaUsaha(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return skalaUsahaRepository.getCount(queryParamFilters);
	}
	
	
}
