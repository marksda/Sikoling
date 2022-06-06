package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.User;

public interface LayananPermohonan {
	Permohonan save(Permohonan permohonan);
	Permohonan update(Permohonan permohonan);
	List<Permohonan> getAll(Integer page, Integer pageSize);
	List<Permohonan> getByUser(User user);
	List<Permohonan> getByPemrakarsa(Pemrakarsa pemrakarsa);
	List<Permohonan> getByProduk(Produk produk);
	List<Permohonan> getByKategoriProduk(KategoriProduk kategoriProduk);	
	List<Permohonan> getByUserAndProduk(User user, Produk produk);
	List<Permohonan> getByUserAndKategoriProduk(User user, KategoriProduk kategoriProduk);
	List<Permohonan> getByUserAndPemrakarsa(User user, Pemrakarsa pemrakarsa);
	List<Permohonan> getByUserAndProdukAndPemrakarsa(User user, Produk produk, Pemrakarsa pemrakarsa);
	List<Permohonan> getByUserAndKategoriProdukAndPemrakarsa(User user, KategoriProduk kategoriProduk, Pemrakarsa pemrakarsa);	
}
