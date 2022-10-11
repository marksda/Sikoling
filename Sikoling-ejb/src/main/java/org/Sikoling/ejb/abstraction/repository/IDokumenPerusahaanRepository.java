package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DokumenPerusahaan;

public interface IDokumenPerusahaanRepository extends IRepository<DokumenPerusahaan> {
	List<DokumenPerusahaan> getAllByPage(Integer page, Integer pageSize);
	List<DokumenPerusahaan> getByNama(String nama);
	List<DokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
