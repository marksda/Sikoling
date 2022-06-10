package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.ProdukDLH;

public interface IProdukRepository extends IRepository<ProdukDLH> {
	List<ProdukDLH> getAll(Integer page, Integer pageSize);
	List<ProdukDLH> getByQueryNama(String nama);
}
