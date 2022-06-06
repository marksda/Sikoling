package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;

public interface IPemrakarsaRepository extends IRepository<Pemrakarsa> {
	List<Pemrakarsa> getAll(Integer page, Integer pageSize);
	List<Pemrakarsa> getByQueryNama(String nama);
}
