package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
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
	public List<KategoriDokumen> getAll() {
		return kategoriDokumenPerusahaanRepository.getAll();
	}

	@Override
	public KategoriDokumen save(KategoriDokumen t) {
		return kategoriDokumenPerusahaanRepository.save(t);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen t) {
		return kategoriDokumenPerusahaanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String Id) {
		return kategoriDokumenPerusahaanRepository.delete(Id);
	}

	@Override
	public KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen) {
		return kategoriDokumenPerusahaanRepository.updateById(id, kategoriDokumen);
	}

	
	@Override
	public List<KategoriDokumen> getDaftarKategoriDokumen(QueryParamFilters queryParamFilters) {
		return kategoriDokumenPerusahaanRepository.getDaftarKategoriDokumen(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kategoriDokumenPerusahaanRepository.getCount(queryParamFilters);
	}

}
