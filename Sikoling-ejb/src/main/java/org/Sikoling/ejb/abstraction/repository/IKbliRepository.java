package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;

public interface IKbliRepository extends IRepository<Kbli> {
	Kbli updateId(String idLama, Kbli t) throws IOException;;
}
