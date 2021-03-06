package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;

public interface IJenisPelakuUsahaRepository extends IRepository<JenisPelakuUsaha> {
	List<JenisPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<JenisPelakuUsaha> getByNama(String nama);
	List<JenisPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
