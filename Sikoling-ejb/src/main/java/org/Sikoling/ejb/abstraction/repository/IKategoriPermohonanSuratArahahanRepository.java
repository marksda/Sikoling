package org.Sikoling.ejb.abstraction.repository;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.JenisPermohonanSuratArahan;

public interface IKategoriPermohonanSuratArahahanRepository extends IRepository<JenisPermohonanSuratArahan> {
	DeleteResponse delete(String id);
}
