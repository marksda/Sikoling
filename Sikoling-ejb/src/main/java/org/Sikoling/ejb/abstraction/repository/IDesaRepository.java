package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

public interface IDesaRepository<T, S> extends IRepository2<T, S> {
	List<T> getAllByPage(Integer page, Integer pageSize);
	List<T> getByNama(String nama);
	List<T> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<T> getByKecamatan(String idKecamatan);
	List<T> getByKecamatanAndPage(String idKecamatan, Integer page, Integer pageSize);
	List<T> getByKecamatanAndNama(String idKecamatan, String nama);
	List<T> getByKecamatanAndNamaAndPage(String idKecamatan, String nama, Integer page, Integer pageSize);
}
