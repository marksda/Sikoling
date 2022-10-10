package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;

public interface ISkalaUsahaRepository extends IRepository<SkalaUsaha> {
	List<SkalaUsaha> getAllByPage(Integer page, Integer pageSize);
	List<SkalaUsaha> getByNama(String nama);
	List<SkalaUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
