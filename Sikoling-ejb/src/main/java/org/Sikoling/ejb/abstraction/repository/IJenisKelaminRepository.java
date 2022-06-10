package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;

public interface IJenisKelaminRepository extends IRepository<JenisKelamin> {
	List<JenisKelamin> getAllByPage(Integer page, Integer pageSize);
	List<JenisKelamin> getByQueryNama(String nama);
	List<JenisKelamin> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
