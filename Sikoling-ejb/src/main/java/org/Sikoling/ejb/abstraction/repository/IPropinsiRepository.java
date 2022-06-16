package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Propinsi;

public interface IPropinsiRepository extends IRepository<Propinsi> {
	List<Propinsi> getAllByPage(Integer page, Integer pageSize);
	List<Propinsi> getByNama(String nama);
	List<Propinsi> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
