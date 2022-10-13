package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.HakAkses;

public interface IHakAksesRepository extends IRepository<HakAkses> {
	List<HakAkses> getAllByPage(Integer page, Integer pageSize);
	List<HakAkses> getByNama(String nama);
	List<HakAkses> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
