package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Propinsi;

public interface IPropinsiRepository extends IRepository<Propinsi> {
	List<Propinsi> getAllByPage(Integer page, Integer pageSize);
	List<Propinsi> getByQueryNama(String nama);
	List<Propinsi> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
