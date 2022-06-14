package org.Sikoling.ejb.main.repository.kategoriproduk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriProdukRepositoryEJB implements IKategoriProdukRepository {
	
	@Inject
	private KategoriProdukRepositoryJPA kategoriProdukRepositoryJPA;

	@Override
	public List<KategoriProduk> getAll() {
		return kategoriProdukRepositoryJPA.getAll();
	}

	@Override
	public KategoriProduk save(KategoriProduk t) {
		return kategoriProdukRepositoryJPA.save(t);
	}

	@Override
	public KategoriProduk update(KategoriProduk t) {
		return kategoriProdukRepositoryJPA.update(t);
	}

	@Override
	public List<KategoriProduk> getAllByPage(Integer page, Integer pageSize) {
		return kategoriProdukRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriProduk> getByQueryNama(String nama) {
		return kategoriProdukRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<KategoriProduk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kategoriProdukRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
