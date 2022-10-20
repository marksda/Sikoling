package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;

public interface IKategoriPelakuUsahaRepository extends IRepository<KategoriPelakuUsaha> {
	List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getByNama(String nama);
	List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<KategoriPelakuUsaha> getALLBySkalaUsaha(String idSkalaUsaha);
}
