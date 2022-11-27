package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;

public interface IKategoriPelakuUsahaServices {
	KategoriPelakuUsaha save(KategoriPelakuUsaha kategoriPelakuUsaha);
	KategoriPelakuUsaha update(KategoriPelakuUsaha kategoriPelakuUsaha);
	KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha);
	DeleteResponse delete(String id);
	List<KategoriPelakuUsaha> getALL();
	List<KategoriPelakuUsaha> getALLBySkalaUsaha(String idSkalaUsaha);
	List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getByNama(String nama);
	List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
