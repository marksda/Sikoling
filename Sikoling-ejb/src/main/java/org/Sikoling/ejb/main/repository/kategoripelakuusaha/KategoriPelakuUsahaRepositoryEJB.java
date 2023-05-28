package org.Sikoling.ejb.main.repository.kategoripelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriPelakuUsahaRepositoryEJB implements IKategoriPelakuUsahaRepository {
	
	@Inject
	private KategoriPelakuUsahaRepositoryJPA kategoriPelakuUsahaRepository;

	@Override
	public List<KategoriPelakuUsaha> getAll() {
		return kategoriPelakuUsahaRepository.getAll();
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
