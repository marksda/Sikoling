package org.Sikoling.ejb.abstraction.service.produk;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.ProdukDLH;

public interface IServiceProduk {
	
	ProdukDLH save(ProdukDLH produk);
	ProdukDLH update(ProdukDLH produk);
	List<ProdukDLH> getAll(Integer page, Integer pageSize);	

}
