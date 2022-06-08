package org.Sikoling.ejb.abstraction.service.kabupaten;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;

public interface IServiceKabupaten {
	Kabupaten save(Kabupaten kabupaten);
	Kabupaten update(Kabupaten kabupaten);	
	List<Kabupaten> getAll();
	List<Kabupaten> getAllByPage(Integer page, Integer pageSize);
	List<Kabupaten> getByQueryNama(String nama);
	List<Kabupaten> getByQueryNamAndPage(String nama, Integer page, Integer pageSize);
	List<Kabupaten> getByIdPropinsi(String idPropinsi);
	List<Kabupaten> getByIdPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize);
	List<Kabupaten> getByIdPropinsiAndQueryNama(String idPropinsi, String nama);
	List<Kabupaten> getByIdPropinsiAndQueryNamaAndPage(String idPropinsi, String nama, Integer page, Integer pageSize);
}
