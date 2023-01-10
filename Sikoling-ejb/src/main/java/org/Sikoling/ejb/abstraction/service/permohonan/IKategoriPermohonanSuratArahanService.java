package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.JenisPermohonanSuratArahan;

public interface IKategoriPermohonanSuratArahanService {
	DeleteResponse delete(String id);
	JenisPermohonanSuratArahan save(JenisPermohonanSuratArahan t);
	JenisPermohonanSuratArahan update(JenisPermohonanSuratArahan t);
	List<JenisPermohonanSuratArahan> getAll();
}
