package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.JenisPermohonanSuratArahan;
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
	public List<JenisPermohonanSuratArahan> getAll() {
		return kategoriPermohonanSuratArahan.getAll();
	}

	@Override
	public JenisPermohonanSuratArahan save(JenisPermohonanSuratArahan t) {
		return kategoriPermohonanSuratArahan.save(t);
	}

	@Override
	public JenisPermohonanSuratArahan update(JenisPermohonanSuratArahan t) {
		return kategoriPermohonanSuratArahan.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriPermohonanSuratArahan.delete(id);
	}
	
}
