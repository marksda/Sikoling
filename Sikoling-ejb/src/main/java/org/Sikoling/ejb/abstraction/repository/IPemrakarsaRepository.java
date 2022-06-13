package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;

public interface IPemrakarsaRepository extends IRepository<Pemrakarsa> {
	List<Pemrakarsa> getAllByPage(Integer page, Integer pageSize);
	List<Pemrakarsa> getByQueryNama(String nama);
	List<Pemrakarsa> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
//	List<Pemrakarsa> getByWali(String idWali);
//	List<Pemrakarsa> getByWaliAndPage(String idWali, Integer page, Integer pageSize);
//	List<Pemrakarsa> getByWaliAndNama(String idWali, String nama);
//	List<Pemrakarsa> getByWaliAndNamaAndPage(String idWali, String nama, Integer page, Integer pageSize);	
}
