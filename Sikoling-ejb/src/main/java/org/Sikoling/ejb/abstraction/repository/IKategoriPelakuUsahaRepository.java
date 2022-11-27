package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;

public interface IKategoriPelakuUsahaRepository extends IRepository<KategoriPelakuUsaha> {
	KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha);
	DeleteResponse delete(String id);
	List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getByNama(String nama);
	List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getALLBySkalaUsaha(String idSkalaUsaha);
}