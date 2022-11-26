package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;

public interface IPerusahaanRepository extends IRepository<Perusahaan> {
	Perusahaan updateStatusVerifikasi(Perusahaan t, boolean statusVerifikasi);
	Boolean cekById(String id);
	Perusahaan updateById(String id, Perusahaan perusahaan);
	DeleteResponse delete(String id);
	List<Perusahaan> getAllByPage(Integer page, Integer pageSize);
	List<Perusahaan> getByNama(String nama);
	List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	Perusahaan getByNpwp(String npwp);
}
