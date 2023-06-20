package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.Produk;

public interface IProdukRepository extends IRepository<Produk> {
	Produk updateId(String idLama, Produk t) throws IOException;
}
