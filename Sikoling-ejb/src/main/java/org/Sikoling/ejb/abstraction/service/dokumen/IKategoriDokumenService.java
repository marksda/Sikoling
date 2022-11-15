package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;

public interface IKategoriDokumenService {
	KategoriDokumen save(KategoriDokumen kategoriDokumen);
	KategoriDokumen update(KategoriDokumen kategoriDokumen);
	List<KategoriDokumen> getAll();
	List<KategoriDokumen> getAllByPage(Integer page, Integer pageSize);
	List<KategoriDokumen> getByNama(String nama);
	List<KategoriDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
