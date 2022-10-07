package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;

public interface IPerusahaanService {
	Perusahaan save(Perusahaan t);
	Perusahaan update(Perusahaan t);
	List<Perusahaan> getAll();
	List<Perusahaan> getAllByPage(Integer page, Integer pageSize);
	List<Perusahaan> getByNama(String nama);
	List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Perusahaan> getByCreator(String idCreator);
	List<Perusahaan> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize);
	List<Perusahaan> getByCreatorAndNama(String idCreator, String nama);
	List<Perusahaan> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize);	
}
