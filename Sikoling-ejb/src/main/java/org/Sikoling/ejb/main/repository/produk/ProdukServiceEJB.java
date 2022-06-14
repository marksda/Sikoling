package org.Sikoling.ejb.main.repository.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class ProdukServiceEJB implements IProdukRepository {
	
	@Inject
	private ProdukRepositoryJPA produkRepositoryJPA;

	@Override
	public List<Produk> getAll() {
		return produkRepositoryJPA.getAll();
	}

	@Override
	public Produk save(Produk t) {
		return produkRepositoryJPA.save(t);
	}

	@Override
	public Produk update(Produk t) {
		return produkRepositoryJPA.update(t);
	}

	@Override
	public List<Produk> getAllByPage(Integer page, Integer pageSize) {
		return produkRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Produk> getByQueryNama(String nama) {
		return produkRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<Produk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return produkRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
