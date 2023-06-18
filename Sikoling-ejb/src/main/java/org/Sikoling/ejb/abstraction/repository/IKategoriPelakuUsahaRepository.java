package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;

public interface IKategoriPelakuUsahaRepository extends IRepository<KategoriPelakuUsaha> {
	KategoriPelakuUsaha updateId(String idLama, KategoriPelakuUsaha t) throws IOException;
}