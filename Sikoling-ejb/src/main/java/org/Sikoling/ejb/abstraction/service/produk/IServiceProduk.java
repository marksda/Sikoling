package org.Sikoling.ejb.abstraction.service.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;

public interface IServiceProduk {
	
	Produk save(Produk produk);
	Produk update(Produk produk);
	List<Produk> getAll(Integer page, Integer pageSize);	

}
