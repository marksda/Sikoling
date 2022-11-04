package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;

public interface IPerusahaanRepository extends IRepository<Perusahaan> {
	Perusahaan updateStatusVerifikasi(Perusahaan t, boolean statusVerifikasi);
	Boolean getById(String id);
	List<Perusahaan> getAllByPage(Integer page, Integer pageSize);
	List<Perusahaan> getByNama(String nama);
	List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Perusahaan> getByCreator(String idCreator);
	List<Perusahaan> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize);
	List<Perusahaan> getByCreatorAndNama(String idCreator, String nama);
	List<Perusahaan> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize);	
}
