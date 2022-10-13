package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

public interface IBidangUsahaRepository<T> extends IRepository<T> {
	List<T> getAllByPage(Integer page, Integer pageSize);
	List<T> getByNama(String nama);
	List<T> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
