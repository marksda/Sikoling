package org.Sikoling.ejb.abstraction.service.skalausaha;

import java.io.IOException;
import java.util.List;

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
	public SkalaUsaha save(SkalaUsaha t) throws IOException {
		return skalaUsahaRepository.save(t);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha t) {
		return skalaUsahaRepository.update(t);
	}

	@Override
	public SkalaUsaha updateId(String id, SkalaUsaha t) throws IOException {
		return skalaUsahaRepository.updateId(id, t);
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

}
