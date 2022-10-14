package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autorisasi;

public interface IAutorisasiRepository extends IRepository<Autorisasi> {
	List<Autorisasi> getAllByPage(Integer page, Integer pageSize);
	List<Autorisasi> getByNama(String nama);
	List<Autorisasi> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
