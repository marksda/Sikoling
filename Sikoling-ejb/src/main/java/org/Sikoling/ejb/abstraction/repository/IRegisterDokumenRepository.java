package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;

public interface IRegisterDokumenRepository extends IRepository<RegisterDokumen> {
	RegisterDokumen updateId(String idLama, RegisterDokumen t) throws IOException;
}