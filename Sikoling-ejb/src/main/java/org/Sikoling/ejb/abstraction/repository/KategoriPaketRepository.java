package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPaket;

public interface KategoriPaketRepository extends Repository<KategoriPaket> {
	List<KategoriPaket> getAll(Integer page, Integer pageSize);
}
