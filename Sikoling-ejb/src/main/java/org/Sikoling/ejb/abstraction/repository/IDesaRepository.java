package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Desa;

public interface IDesaRepository extends IRepository<Desa> {
	Desa updateId(String idLama, Desa t) throws IOException;
}
