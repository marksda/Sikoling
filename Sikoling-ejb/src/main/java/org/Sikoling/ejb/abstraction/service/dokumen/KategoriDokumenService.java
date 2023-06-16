package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

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
	public KategoriDokumen save(KategoriDokumen t) throws IOException {
		return kategoriDokumenRepository.save(t);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen t) {
		return kategoriDokumenRepository.update(t);
	}

	@Override
	public KategoriDokumen updateId(String idLama, KategoriDokumen t) throws IOException {
		return kategoriDokumenRepository.updateId(idLama, t);
	}

	@Override
	public KategoriDokumen delete(KategoriDokumen t) throws IOException {
		return kategoriDokumenRepository.delete(t);
	}

	@Override
	public List<KategoriDokumen> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriDokumenRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriDokumenRepository.getJumlahData(queryParamFilters);
	}

}
