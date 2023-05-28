package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	public List<PelakuUsaha> getALL() {
		return pelakuUsahaRepository.getAll();
	}

	@Override
	public PelakuUsaha save(PelakuUsaha pelakuUsaha) {
		return pelakuUsahaRepository.save(pelakuUsaha);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha pelakuUsaha) {
		return pelakuUsahaRepository.save(pelakuUsaha);
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
