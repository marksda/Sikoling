package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;

public interface IProdukRepository extends IRepository<Produk> {
	List<Produk> getAllByPage(Integer page, Integer pageSize);
	List<Produk> getByQueryNama(String nama);
	List<Produk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
