package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public interface BentukUsahaRepository extends Repository<BentukUsaha> {
	List<BentukUsaha> getAll(Integer page, Integer pageSize);
	List<BentukUsaha> getByQueryNama(String nama);
}
