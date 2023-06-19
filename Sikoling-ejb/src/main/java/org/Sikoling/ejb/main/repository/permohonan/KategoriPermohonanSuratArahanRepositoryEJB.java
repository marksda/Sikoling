package org.Sikoling.ejb.main.repository.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanSuratArahahanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriPermohonanSuratArahanRepositoryEJB implements IKategoriPermohonanSuratArahahanRepository {

	@Inject
	private KategoriPermohonanSuratArahanRepositoryJPA kategoriPermohonanSuratArahan;

	@Override
	public KategoriPermohonanSuratArahan save(KategoriPermohonanSuratArahan t) throws IOException {
		return kategoriPermohonanSuratArahan.save(t);
	}

	@Override
	public KategoriPermohonanSuratArahan update(KategoriPermohonanSuratArahan t) {
		return kategoriPermohonanSuratArahan.update(t);
	}

	@Override
	public KategoriPermohonanSuratArahan delete(KategoriPermohonanSuratArahan t) throws IOException {
		return kategoriPermohonanSuratArahan.delete(t);
	}

	@Override
	public List<KategoriPermohonanSuratArahan> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriPermohonanSuratArahan.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriPermohonanSuratArahan.getJumlahData(queryParamFilters);
	}

	@Override
	public KategoriPermohonanSuratArahan updateId(String idLama, KategoriPermohonanSuratArahan t) throws IOException {
		return kategoriPermohonanSuratArahan.updateId(idLama, t);
	}

}
