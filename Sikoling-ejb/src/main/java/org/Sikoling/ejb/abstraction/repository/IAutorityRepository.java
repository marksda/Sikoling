package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Otoritas;

public interface IAutorityRepository extends IRepository<Otoritas> {	
	Otoritas getByUserName(String userName);
	Otoritas updateId(String idLama, Otoritas t) throws IOException;
}
