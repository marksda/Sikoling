package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public interface IKabupatenRepository extends IRepository<Kabupaten> {
	List<Kabupaten> getAllByPage(Integer page, Integer pageSize);
	List<Kabupaten> getByQueryNama(String nama);
	List<Kabupaten> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Kabupaten> getByPropinsi(String idPropinsi);
	List<Kabupaten> getByPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize);
	List<Kabupaten> getByPropinsiAndQueryNama(String idPropinsi, String nama);
	List<Kabupaten> getByPropinsiAndQueryNamaAndPage(String idPropinsi, String nama, Integer page, Integer pageSize);
}
