package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;

public interface IRegisterPerusahaanRepository extends IRepository<RegisterPerusahaan> {
	RegisterPerusahaan delete(RegisterPerusahaan t, Otoritas userOtoritas) throws IOException;	
	RegisterPerusahaan updateId(String idLama, RegisterPerusahaan t) throws IOException;
}