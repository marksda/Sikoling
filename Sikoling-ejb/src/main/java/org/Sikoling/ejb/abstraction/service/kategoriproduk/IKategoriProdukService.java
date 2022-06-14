package org.Sikoling.ejb.abstraction.service.kategoriproduk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;

public interface IKategoriProdukService {
	KategoriProduk save(KategoriProduk t);
	KategoriProduk update(KategoriProduk t);
	List<KategoriProduk> getAll();
	List<KategoriProduk> getAllByPage(Integer page, Integer pageSize);
	List<KategoriProduk> getByQueryNama(String nama);
	List<KategoriProduk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);

}
