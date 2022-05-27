package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Propinsi;

public interface PropinsiRepository extends Repository<Propinsi> {
	List<Propinsi> getAll(Integer page, Integer pageSize);
	List<Propinsi> getByQueryNama(String nama);
}
