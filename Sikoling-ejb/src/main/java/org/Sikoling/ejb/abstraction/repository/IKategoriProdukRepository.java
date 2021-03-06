package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;

public interface IKategoriProdukRepository extends IRepository<KategoriProduk> {
	List<KategoriProduk> getAllByPage(Integer page, Integer pageSize);
	List<KategoriProduk> getByQueryNama(String nama);
	List<KategoriProduk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
