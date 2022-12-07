package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;

public interface IPerusahaanService {
	DeleteResponse delete(String id);
	Perusahaan save(Perusahaan t);
	Perusahaan update(Perusahaan t);
	Perusahaan updateById(String id, Perusahaan perusahaan);
	Boolean cekById(String id);
	Perusahaan getByNpwp(String npwp);
	List<Perusahaan> getAll();
	List<Perusahaan> getAllByPage(Integer page, Integer pageSize);
	List<Perusahaan> getByNama(String nama);
	List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Perusahaan> getByIdPerson(String personId);
}
