package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public interface IBentukUsahaRepository extends IRepository<BentukUsaha> {
	List<BentukUsaha> getAllByPage(Integer page, Integer pageSize);
	List<BentukUsaha> getByQueryNama(String nama);
	List<BentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
