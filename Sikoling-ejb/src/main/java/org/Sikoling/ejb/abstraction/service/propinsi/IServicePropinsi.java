package org.Sikoling.ejb.abstraction.service.propinsi;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Propinsi;

public interface IServicePropinsi {
	
	List<Propinsi> getAll();
	Propinsi save(Propinsi propinsi);
	Propinsi update(Propinsi propinsi);
	List<Propinsi> getAllByPage(Integer page, Integer pageSize);
	List<Propinsi> getByQueryNama(String nama);
	List<Propinsi> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
	
}
