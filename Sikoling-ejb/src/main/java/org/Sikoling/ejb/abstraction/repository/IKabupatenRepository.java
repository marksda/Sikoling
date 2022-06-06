package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public interface IKabupatenRepository extends IRepository<Kabupaten> {
	List<Kabupaten> getAll(Integer page, Integer pageSize);
	List<Kabupaten> getByPropinsi(String idPropinsi);
	List<Kabupaten> getByPropinsiAndQueryNama(String idPropinsi, String nama);

}
