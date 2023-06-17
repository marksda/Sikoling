package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Jabatan;

public interface IJabatanRepository extends IRepository<Jabatan> {
	Jabatan updateId(String idLama, Jabatan t) throws IOException;
}
