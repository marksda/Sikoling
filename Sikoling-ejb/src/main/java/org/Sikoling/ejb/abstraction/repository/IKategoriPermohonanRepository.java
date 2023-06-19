package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;

public interface IKategoriPermohonanRepository extends IRepository<KategoriPermohonan> {
	KategoriPermohonan updateId(String idLama, KategoriPermohonan t) throws IOException;
}
