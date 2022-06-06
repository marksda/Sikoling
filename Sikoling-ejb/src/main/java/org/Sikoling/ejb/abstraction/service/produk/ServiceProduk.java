package org.Sikoling.ejb.abstraction.service.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;

public class ServiceProduk implements IServiceProduk {
	
	private final IProdukRepository produkRepository;

	public ServiceProduk(IProdukRepository produkRepository) {
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
	public List<Produk> getAll(Integer page, Integer pageSize) {
		return null;
	}

}
