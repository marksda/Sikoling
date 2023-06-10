package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;

public class KategoriDokumenService implements IKategoriDokumenService {

	private final IKategoriDokumenRepository kategoriDokumenRepository;	
	
	public KategoriDokumenService(IKategoriDokumenRepository kategoriDokumenRepository) {
		this.kategoriDokumenRepository = kategoriDokumenRepository;
	}

	@Override
	public KategoriDokumen save(KategoriDokumen kategoriDokumen) {
		return kategoriDokumenRepository.save(kategoriDokumen);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen kategoriDokumen) {
		return kategoriDokumenRepository.update(kategoriDokumen);
	}

	@Override
	public DeleteResponse delete(String Id) {
		return kategoriDokumenRepository.delete(Id);
	}
	
	@Override
	public KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen) {
		return kategoriDokumenRepository.updateById(id, kategoriDokumen);
	}

	
	@Override
	public List<KategoriDokumen> getDaftarKategoriDokumen(QueryParamFilters queryParamFilters) {
		return kategoriDokumenRepository.getDaftarKategoriDokumen(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kategoriDokumenRepository.getCount(queryParamFilters);
	}

}
