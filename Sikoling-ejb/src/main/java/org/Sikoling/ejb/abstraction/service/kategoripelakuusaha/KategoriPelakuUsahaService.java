package org.Sikoling.ejb.abstraction.service.kategoripelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;

public class KategoriPelakuUsahaService implements IKategoriPelakuUsahaServices {
	
	private final IKategoriPelakuUsahaRepository kategoriPelakuUsahaRepository;	

	public KategoriPelakuUsahaService(IKategoriPelakuUsahaRepository kategoriPelakuUsahaRepository) {
		this.kategoriPelakuUsahaRepository = kategoriPelakuUsahaRepository;
	}

	@Override
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) {
		return kategoriPelakuUsahaRepository.save(t);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		return kategoriPelakuUsahaRepository.update(t);
	}
	
	@Override
	public KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha) {
		return kategoriPelakuUsahaRepository.updateById(id, kategoriPelakuUsaha);
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriPelakuUsahaRepository.delete(id);
	}

	
	@Override
	public List<KategoriPelakuUsaha> getDaftarKategoriPelakuUsaha(QueryParamFilters queryParamFilters) {
		return kategoriPelakuUsahaRepository.getDaftarKategoriPelakuUsaha(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kategoriPelakuUsahaRepository.getCount(queryParamFilters);
	}

	

}
