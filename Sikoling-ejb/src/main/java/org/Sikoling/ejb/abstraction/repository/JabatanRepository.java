package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Jabatan;

public interface JabatanRepository extends Repository<Jabatan> {
	List<Jabatan> getAll(Integer page, Integer pageSize);	
	List<Jabatan> getByQueryNama(String nama);
}
