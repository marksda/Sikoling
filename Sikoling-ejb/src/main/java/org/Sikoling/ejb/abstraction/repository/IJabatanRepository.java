package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Jabatan;

public interface IJabatanRepository extends IRepository<Jabatan> {
	List<Jabatan> getAllByPage(Integer page, Integer pageSize);	
	List<Jabatan> getByNama(String nama);
	List<Jabatan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
