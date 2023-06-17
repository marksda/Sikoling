package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Autority;

public interface IAutorityRepository extends IRepository<Autority> {	
	Autority getByUserName(String userName);
	Autority updateId(String idLama, Autority t) throws IOException;
}
