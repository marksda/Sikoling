package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;

public interface IKecamatanRepository extends IRepository<Kecamatan> {
	Kecamatan updateId(String idLama, Kecamatan t) throws IOException;
}
