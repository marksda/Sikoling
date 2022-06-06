package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;

public interface IProdukRepository extends IRepository<Produk> {
	List<Produk> getAll(Integer page, Integer pageSize);
	List<Produk> getByQueryNama(String nama);
}
