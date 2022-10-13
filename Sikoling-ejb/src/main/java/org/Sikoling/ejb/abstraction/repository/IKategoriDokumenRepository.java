package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;

public interface IKategoriDokumenRepository extends IRepository2<KategoriDokumen, String> {
	List<KategoriDokumen> getAllByPage(Integer page, Integer pageSize);
	List<KategoriDokumen> getByNama(String nama);
	List<KategoriDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
