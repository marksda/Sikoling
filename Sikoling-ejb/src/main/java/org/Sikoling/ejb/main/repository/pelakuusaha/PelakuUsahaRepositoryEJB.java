package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class PelakuUsahaRepositoryEJB implements IPelakuUsahaRepository {
	
	@Inject
	private PelakuUsahaRepositoryJPA pelakuUsahaRepository;

	@Override
	public List<PelakuUsaha> getAll() {
		return pelakuUsahaRepository.getAll();
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) {
		return pelakuUsahaRepository.save(t);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		return pelakuUsahaRepository.update(t);
	}

	@Override
	public PelakuUsaha updateById(String id, PelakuUsaha pelakuUsaha) {
		return pelakuUsahaRepository.updateById(id, pelakuUsaha);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		return pelakuUsahaRepository.delete(id);
	}

	
	@Override
	public List<PelakuUsaha> getDaftarPelakuUsaha(QueryParamFilters queryParamFilters) {
		return pelakuUsahaRepository.getDaftarPelakuUsaha(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return pelakuUsahaRepository.getCount(queryParamFilters);
	}

}
