package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public interface IKabupatenRepository extends IRepository<Kabupaten> {
	Kabupaten updateId(String idLama, Kabupaten t) throws IOException;
}
