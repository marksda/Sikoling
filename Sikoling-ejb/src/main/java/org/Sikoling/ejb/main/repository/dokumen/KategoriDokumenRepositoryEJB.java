package org.Sikoling.ejb.main.repository.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriDokumenRepositoryEJB implements IKategoriDokumenRepository {

	@Inject
	private KategoriDokumenRepositoryJPA kategoriDokumenPerusahaanRepository;

	@Override
	public KategoriDokumen save(KategoriDokumen t) throws IOException {
		return kategoriDokumenPerusahaanRepository.save(t);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen t) {
		return kategoriDokumenPerusahaanRepository.update(t);
	}

	@Override
	public KategoriDokumen delete(KategoriDokumen t) throws IOException {
		return kategoriDokumenPerusahaanRepository.delete(t);
	}

	@Override
	public List<KategoriDokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriDokumenPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriDokumenPerusahaanRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public KategoriDokumen updateId(String idLama, KategoriDokumen t) throws IOException {
		return kategoriDokumenPerusahaanRepository.updateId(idLama, t);
	}
	

}
