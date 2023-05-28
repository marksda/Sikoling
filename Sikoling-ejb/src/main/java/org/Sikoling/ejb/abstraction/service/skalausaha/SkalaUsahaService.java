package org.Sikoling.ejb.abstraction.service.skalausaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;

public class SkalaUsahaService implements ISkalaUsahaService {
	
	private final ISkalaUsahaRepository skalaUsahaRepository;	

	public SkalaUsahaService(ISkalaUsahaRepository skalaUsahaRepository) {
		this.skalaUsahaRepository = skalaUsahaRepository;
	}

	@Override
	public List<SkalaUsaha> getALL() {
		return skalaUsahaRepository.getAll();
	}

	@Override
	public SkalaUsaha save(SkalaUsaha skalaUsaha) {
		return skalaUsahaRepository.save(skalaUsaha);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha skalaUsaha) {
		return skalaUsahaRepository.update(skalaUsaha);
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
