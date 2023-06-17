package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public interface IMasterDokumenRepository extends IRepository<Dokumen> {
	Dokumen updateId(String idLama, Dokumen t) throws IOException;
}
