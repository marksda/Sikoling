package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanSuratArahahanRepository;

public class KategoriPermohonanSuratArahanService implements IKategoriPermohonanSuratArahanService {
	
	private final IKategoriPermohonanSuratArahahanRepository kategoriPermohonanSuratArahahanRepository;	

	public KategoriPermohonanSuratArahanService(IKategoriPermohonanSuratArahahanRepository kategoriPermohonanSuratArahahanRepository) {
		this.kategoriPermohonanSuratArahahanRepository = kategoriPermohonanSuratArahahanRepository;
	}

	@Override
	public KategoriPermohonanSuratArahan save(KategoriPermohonanSuratArahan t) throws IOException {
		return kategoriPermohonanSuratArahahanRepository.save(t);
	}

	@Override
	public KategoriPermohonanSuratArahan update(KategoriPermohonanSuratArahan t) {
		return kategoriPermohonanSuratArahahanRepository.update(t);
	}

	@Override
	public KategoriPermohonanSuratArahan updateId(String idLama, KategoriPermohonanSuratArahan t) throws IOException {
		return kategoriPermohonanSuratArahahanRepository.updateId(idLama, t);
	}

	@Override
	public KategoriPermohonanSuratArahan delete(KategoriPermohonanSuratArahan t) throws IOException {
		return kategoriPermohonanSuratArahahanRepository.delete(t);
	}

	@Override
	public List<KategoriPermohonanSuratArahan> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriPermohonanSuratArahahanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriPermohonanSuratArahahanRepository.getJumlahData(queryParamFilters);
	}

	
	
}
