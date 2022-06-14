package org.Sikoling.ejb.abstraction.service.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Produk;

public interface IProdukService {	
	Produk save(Produk produk);
	Produk update(Produk produk);
	List<Produk> getAll();	
	List<Produk> getAllByPage(Integer page, Integer pageSize);
	List<Produk> getByQueryNama(String nama);
	List<Produk> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
