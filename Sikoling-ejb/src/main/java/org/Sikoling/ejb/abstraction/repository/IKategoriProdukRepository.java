package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;

public interface IKategoriProdukRepository extends IRepository<KategoriProduk> {
	KategoriProduk updateId(String idLama, KategoriProduk t) throws IOException;
}