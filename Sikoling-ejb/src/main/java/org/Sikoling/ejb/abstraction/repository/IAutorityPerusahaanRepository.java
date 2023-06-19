package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.AutorityPerusahaan;

public interface IAutorityPerusahaanRepository extends IRepository<AutorityPerusahaan> {
	AutorityPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, AutorityPerusahaan t) throws IOException;
}