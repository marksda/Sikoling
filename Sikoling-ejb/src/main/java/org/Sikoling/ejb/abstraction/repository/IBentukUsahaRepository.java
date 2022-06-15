package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public interface IBentukUsahaRepository extends IRepository<BentukUsaha> {
	List<BentukUsaha> getAllByPage(Integer page, Integer pageSize);
	List<BentukUsaha> getByNama(String nama);
	List<BentukUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<BentukUsaha> getByPelakuUsaha(String idKelompokBentukUsaha);
	List<BentukUsaha> getByPelakuUsahaAndPage(String idKelompokBentukUsaha, Integer page, Integer pageSize);
	List<BentukUsaha> getByPelakuUsahaAndNama(String idKelompokBentukUsaha, String nama);
	List<BentukUsaha> getByPelakuUsahaAndNamaAndPage(String idKelompokBentukUsaha, String nama, Integer page, Integer pageSize);
}
