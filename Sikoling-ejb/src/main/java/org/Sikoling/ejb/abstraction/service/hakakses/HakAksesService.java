package org.Sikoling.ejb.abstraction.service.hakakses;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IHakAksesRepository;

public class HakAksesService implements IHakAksesService {
	
	private final IHakAksesRepository hakAksesRepository;

	public HakAksesService(IHakAksesRepository hakAksesRepository) {
		this.hakAksesRepository = hakAksesRepository;
	}

	@Override
	public HakAkses save(HakAkses t) {
		return hakAksesRepository.save(t);
	}

	@Override
	public HakAkses update(HakAkses t) {
		return hakAksesRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return hakAksesRepository.delete(id);
	}

	@Override
	public List<HakAkses> getDaftarHakAkses(QueryParamFilters queryParamFilters) {
		return hakAksesRepository.getDaftarHakAkses(queryParamFilters);
	}

	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return hakAksesRepository.getCount(queryParamFilters);
	}

}
