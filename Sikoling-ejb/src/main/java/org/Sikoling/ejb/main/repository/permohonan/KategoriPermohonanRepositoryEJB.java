package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriPermohonanRepositoryEJB implements IKategoriPermohonanRepository {
	
	@Inject
	private KategoriPermohonanRepositoryJPA kategoriPermohonanRepository;

	@Override
	public KategoriPermohonan save(KategoriPermohonan t) throws IOException {
		return kategoriPermohonanRepository.save(t);
	}

	@Override
	public KategoriPermohonan update(KategoriPermohonan t) {
		return kategoriPermohonanRepository.update(t);
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

	@Override
	public KategoriPermohonan updateId(String idLama, KategoriPermohonan t) throws IOException {
		return kategoriPermohonanRepository.updateId(idLama, t);
	}

	
	
}
