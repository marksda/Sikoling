package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public interface IKabupatenRepository extends IRepository2<Kabupaten, String> {
	List<Kabupaten> getAllByPage(Integer page, Integer pageSize);
	List<Kabupaten> getByNama(String nama);
	List<Kabupaten> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Kabupaten> getByPropinsi(String idPropinsi);
	List<Kabupaten> getByPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize);
	List<Kabupaten> getByPropinsiAndNama(String idPropinsi, String nama);
	List<Kabupaten> getByPropinsiAndNamaAndPage(String idPropinsi, String nama, Integer page, Integer pageSize);
}
