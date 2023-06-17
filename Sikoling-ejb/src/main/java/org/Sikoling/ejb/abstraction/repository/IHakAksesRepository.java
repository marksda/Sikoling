package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.HakAkses;

public interface IHakAksesRepository extends IRepository<HakAkses> {
	HakAkses updateId(String idLama, HakAkses t) throws IOException;
}
