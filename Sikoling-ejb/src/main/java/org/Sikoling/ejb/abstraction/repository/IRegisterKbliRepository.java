package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;

public interface IRegisterKbliRepository extends IRepository<RegisterKbli> {
	RegisterKbli updateId(String idNibLama, String idKbliLama, RegisterKbli t) throws IOException;
}
