package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;

public interface IJenisKelaminRepository extends IRepository<JenisKelamin> {
	JenisKelamin updateId(String idLama, JenisKelamin t) throws IOException;
}
