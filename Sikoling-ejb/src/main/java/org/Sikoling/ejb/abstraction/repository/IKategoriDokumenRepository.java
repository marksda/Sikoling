package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;

public interface IKategoriDokumenRepository extends IRepository<KategoriDokumen> {
	DeleteResponse delete(String Id);
	KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen);
	List<KategoriDokumen> getAllByPage(Integer page, Integer pageSize);
	List<KategoriDokumen> getByNama(String nama);
	List<KategoriDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
