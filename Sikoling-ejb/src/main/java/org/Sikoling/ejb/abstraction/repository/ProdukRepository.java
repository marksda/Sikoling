package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;

public interface ProdukRepository extends Repository<Produk> {
	List<Produk> getAll(Integer page, Integer pageSize);
	List<Produk> getByQueryNama(String nama);
}
