package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;

public interface IKategoriProdukRepository extends IRepository<KategoriProduk> {
	List<KategoriProduk> getAll(Integer page, Integer pageSize);
}
