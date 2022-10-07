package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.User;

public interface IPermohonanRepository extends IRepository<Permohonan> {	
	
	List<Permohonan> getAll(Integer page, Integer pageSize);
	List<Permohonan> getByUser(User user);
	List<Permohonan> getByPemrakarsa(Perusahaan pemrakarsa);
	List<Permohonan> getByProduk(Produk produk);
	List<Permohonan> getByKategoriProduk(KategoriProduk kategoriProduk);	
	List<Permohonan> getByUserAndProduk(User user, Produk produk);
	List<Permohonan> getByUserAndKategoriProduk(User user, KategoriProduk kategoriProduk);
	List<Permohonan> getByUserAndPemrakarsa(User user, Perusahaan pemrakarsa);
	List<Permohonan> getByUserAndProdukAndPemrakarsa(User user, Produk produk, Perusahaan pemrakarsa);
	List<Permohonan> getByUserAndKategoriProdukAndPemrakarsa(User user, KategoriProduk kategoriProduk, Perusahaan pemrakarsa);
	
}
