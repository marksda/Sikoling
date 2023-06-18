package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;

public interface IPelakuUsahaRepository extends IRepository<PelakuUsaha> {
	PelakuUsaha updateId(String idLama, PelakuUsaha t) throws IOException;
}
