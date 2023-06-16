package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;

public interface IKategoriDokumenRepository extends IRepository<KategoriDokumen> {
	KategoriDokumen updateId(String idLama, KategoriDokumen t) throws IOException;
}
