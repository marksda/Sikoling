package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.JenisPermohonanSuratArahan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanSuratArahahanRepository;

public class KategoriPermohonanSuratArahanService implements IKategoriPermohonanSuratArahanService {
	
	private final IKategoriPermohonanSuratArahahanRepository kategoriPermohonanSuratArahahanRepository;	

	public KategoriPermohonanSuratArahanService(
			IKategoriPermohonanSuratArahahanRepository kategoriPermohonanSuratArahahanRepository) {
		this.kategoriPermohonanSuratArahahanRepository = kategoriPermohonanSuratArahahanRepository;
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriPermohonanSuratArahahanRepository.delete(id);
	}

	@Override
	public JenisPermohonanSuratArahan save(JenisPermohonanSuratArahan t) {
		return kategoriPermohonanSuratArahahanRepository.save(t);
	}

	@Override
	public JenisPermohonanSuratArahan update(JenisPermohonanSuratArahan t) {
		return kategoriPermohonanSuratArahahanRepository.update(t);
	}

	@Override
	public List<JenisPermohonanSuratArahan> getAll() {
		return kategoriPermohonanSuratArahahanRepository.getAll();
	}

}
