package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;

public interface IOtoritasPerusahaanRepository extends IRepository<OtoritasPerusahaan> {
	OtoritasPerusahaan updateId(String idLamaAutority, String idLamaRegisterPerusahaan, OtoritasPerusahaan t) throws IOException;
}