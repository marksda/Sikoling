package org.Sikoling.ejb.abstraction.service.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;

public class ProdukService implements IProdukService {
	
	private final IProdukRepository produkRepository;

	public ProdukService(IProdukRepository produkRepository) {
		this.produkRepository = produkRepository;
	}

	@Override
	public Produk save(Produk produk) {
		return this.produkRepository.save(produk);
	}

	@Override
	public Produk update(Produk produk) {
		return this.produkRepository.update(produk);
	}
	

	@Override
	public List<Produk> getAll() {
		return produkRepository.getAll();
	}
	

	@Override
	public List<Produk> getAllByPage(Integer page, Integer pageSize) {
		return produkRepository.getAllByPage(page, pageSize);
	}
	

	@Override
	public List<Produk> getByQueryNama(String nama) {
		return produkRepository.getByQueryNama(nama);
	}
	

	@Override
	public List<Produk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return produkRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	
}
