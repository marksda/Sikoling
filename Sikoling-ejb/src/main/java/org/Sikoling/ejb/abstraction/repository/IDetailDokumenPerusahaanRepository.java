package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailDokumenPerusahaan;

public interface IDetailDokumenPerusahaanRepository extends IRepository<DetailDokumenPerusahaan> {
	List<DetailDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize);
	List<DetailDokumenPerusahaan> getByNama(String nama);
	List<DetailDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
