package org.Sikoling.ejb.abstraction.service.kategoripelakuusaha;

import java.io.IOException;
import java.util.List;

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
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) throws IOException {
		return kategoriPelakuUsahaRepository.save(t);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		return kategoriPelakuUsahaRepository.update(t);
	}

	@Override
	public KategoriPelakuUsaha updateId(String idLama, KategoriPelakuUsaha t) throws IOException {
		return kategoriPelakuUsahaRepository.updateId(idLama, t);
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

}
