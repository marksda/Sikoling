package org.Sikoling.ejb.abstraction.service.jenispelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;

public interface IJenisPelakuUsahaService {
	KategoriPelakuUsaha save(KategoriPelakuUsaha t);
	KategoriPelakuUsaha update(KategoriPelakuUsaha t);
	List<KategoriPelakuUsaha> getAll();
	List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getByNama(String nama);
	List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
