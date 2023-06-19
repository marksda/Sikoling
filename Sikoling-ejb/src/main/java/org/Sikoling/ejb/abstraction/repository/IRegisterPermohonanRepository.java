package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;

public interface IRegisterPermohonanRepository extends IRepository<RegisterPermohonan> {
	RegisterPermohonan updateId(String idLama, RegisterPermohonan t) throws IOException;
}