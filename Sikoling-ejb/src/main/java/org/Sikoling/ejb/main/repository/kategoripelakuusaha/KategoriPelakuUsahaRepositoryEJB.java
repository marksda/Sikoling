package org.Sikoling.ejb.main.repository.kategoripelakuusaha;

import java.io.IOException;
import java.util.List;

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
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) throws IOException {
		return kategoriPelakuUsahaRepository.save(t);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		return kategoriPelakuUsahaRepository.update(t);
	}

	@Override
	public KategoriPelakuUsaha delete(KategoriPelakuUsaha t) throws IOException {
		return kategoriPelakuUsahaRepository.delete(t);
	}

	@Override
	public List<KategoriPelakuUsaha> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriPelakuUsahaRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriPelakuUsahaRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public KategoriPelakuUsaha updateId(String idLama, KategoriPelakuUsaha t) throws IOException {
		return kategoriPelakuUsahaRepository.updateId(idLama, t);
	}


}
