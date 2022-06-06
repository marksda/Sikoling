package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPaket;

public interface IKategoriPaketRepository extends IRepository<KategoriPaket> {
	List<KategoriPaket> getAll(Integer page, Integer pageSize);
}
