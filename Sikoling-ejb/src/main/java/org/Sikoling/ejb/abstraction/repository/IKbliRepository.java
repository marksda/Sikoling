package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;

public interface IKbliRepository extends IRepository<Kbli2020> {
	Kbli2020 updateId(String idLama, Kbli2020 t) throws IOException;;
}
