package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public interface KabupatenRepository extends Repository<Kabupaten> {
	List<Kabupaten> getAll(Integer page, Integer pageSize);
	List<Kabupaten> getByPropinsi(String idPropinsi);
	List<Kabupaten> getByPropinsiAndQueryNama(String idPropinsi, String nama);

}
