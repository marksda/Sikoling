package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class KategoriPermohonanRepositoryEJB implements IKategoriPermohonanRepository {
	
	@Inject
	private KategoriPermohonanRepositoryJPA kategoriPermohonanRepository;

	@Override
	public List<KategoriPermohonan> getAll() {
		return kategoriPermohonanRepository.getAll();
	}

	@Override
	public KategoriPermohonan save(KategoriPermohonan t) {
		return kategoriPermohonanRepository.save(t);
	}

	@Override
	public KategoriPermohonan update(KategoriPermohonan t) {
		return kategoriPermohonanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriPermohonanRepository.delete(id);
	}

	
	@Override
	public List<KategoriPermohonan> getDaftarKategoriPermohonan(QueryParamFilters queryParamFilters) {
		return kategoriPermohonanRepository.getDaftarKategoriPermohonan(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kategoriPermohonanRepository.getCount(queryParamFilters);
	}

}
