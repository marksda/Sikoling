package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;

public class KategoriPermohonanService implements IKategoriPermohonanService {
	
	private final IKategoriPermohonanRepository kategoriPermohonanRepository;

	public KategoriPermohonanService(IKategoriPermohonanRepository kategoriPermohonanRepository) {
		this.kategoriPermohonanRepository = kategoriPermohonanRepository;
	}

	@Override
	public KategoriPermohonan save(KategoriPermohonan t) throws IOException {
		return kategoriPermohonanRepository.save(t);
	}

	@Override
	public KategoriPermohonan update(KategoriPermohonan t) {
		return kategoriPermohonanRepository.update(t);
	}

	@Override
	public KategoriPermohonan updateId(String idLama, KategoriPermohonan t) throws IOException {
		return kategoriPermohonanRepository.updateId(idLama, t);
	}

	@Override
	public KategoriPermohonan delete(KategoriPermohonan t) throws IOException {
		return kategoriPermohonanRepository.delete(t);
	}

	@Override
	public List<KategoriPermohonan> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriPermohonanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriPermohonanRepository.getJumlahData(queryParamFilters);
	}
	
}
