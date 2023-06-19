package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;

import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;

public interface IKategoriPermohonanSuratArahahanRepository extends IRepository<KategoriPermohonanSuratArahan> {
	KategoriPermohonanSuratArahan updateId(String idLama, KategoriPermohonanSuratArahan t) throws IOException;
}
