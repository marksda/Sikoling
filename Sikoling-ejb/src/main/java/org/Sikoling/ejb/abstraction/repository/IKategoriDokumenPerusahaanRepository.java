package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;

public interface IKategoriDokumenPerusahaanRepository extends IRepository<KategoriDokumenPerusahaan> {
	List<KategoriDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize);
	List<KategoriDokumenPerusahaan> getByNama(String nama);
	List<KategoriDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
