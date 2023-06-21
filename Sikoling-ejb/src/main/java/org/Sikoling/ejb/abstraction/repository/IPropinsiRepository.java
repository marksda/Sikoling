package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Propinsi;

public interface IPropinsiRepository extends IRepository<Propinsi> {
	Propinsi updateId(String idLama, Propinsi t) throws IOException;
}
