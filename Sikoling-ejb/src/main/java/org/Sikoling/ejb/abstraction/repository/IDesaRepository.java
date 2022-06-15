package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;

public interface IDesaRepository extends IRepository<Desa> {
	List<Desa> getAllByPage(Integer page, Integer pageSize);
	List<Desa> getByNama(String nama);
	List<Desa> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Desa> getByKecamatan(String idKecamatan);
	List<Desa> getByKecamatanAndPage(String idKecamatan, Integer page, Integer pageSize);
	List<Desa> getByKecamatanAndNama(String idKecamatan, String nama);
	List<Desa> getByKecamatanAndNamaAndPage(String idKecamatan, String nama, Integer page, Integer pageSize);
}
