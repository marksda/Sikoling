package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;

public interface KategoriProdukRepository extends Repository<KategoriProduk> {
	List<KategoriProduk> getAll(Integer page, Integer pageSize);
}
