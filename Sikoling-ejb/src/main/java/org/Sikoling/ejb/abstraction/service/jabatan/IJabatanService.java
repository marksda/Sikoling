package org.Sikoling.ejb.abstraction.service.jabatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Jabatan;

public interface IJabatanService {
	Jabatan save(Jabatan jabatan);
	Jabatan update(Jabatan jabatan);
	List<Jabatan> getAll();
	List<Jabatan> getAllByPage(Integer page, Integer pageSize);
	List<Jabatan> getByNama(String nama);
	List<Jabatan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
