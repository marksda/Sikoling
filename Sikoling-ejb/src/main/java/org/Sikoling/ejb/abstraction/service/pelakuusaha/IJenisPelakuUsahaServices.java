package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;

public interface IJenisPelakuUsahaServices {
	List<KategoriPelakuUsaha> getALL();
	KategoriPelakuUsaha save(KategoriPelakuUsaha jenisPelakuUsaha);
	KategoriPelakuUsaha update(KategoriPelakuUsaha jenisPelakuUsaha);
	List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getByNama(String nama);
	List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
