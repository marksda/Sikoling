package org.Sikoling.ejb.abstraction.service.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.ProdukDLH;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;

public class ServiceProduk implements IServiceProduk {
	
	private final IProdukRepository produkRepository;

	public ServiceProduk(IProdukRepository produkRepository) {
		this.produkRepository = produkRepository;
	}

	@Override
	public ProdukDLH save(ProdukDLH produk) {
		return this.produkRepository.save(produk);
	}

	@Override
	public ProdukDLH update(ProdukDLH produk) {
		return this.produkRepository.update(produk);
	}

	@Override
	public List<ProdukDLH> getAll(Integer page, Integer pageSize) {
		return null;
	}

}
