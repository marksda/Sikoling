package org.Sikoling.ejb.abstraction.service.bentukusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public interface IBentukUsahaService {
	BentukUsaha save(BentukUsaha bentukUsaha);
	BentukUsaha update(BentukUsaha bentukUsaha);
	List<BentukUsaha> getAll();
	List<BentukUsaha> getAllByPage(Integer page, Integer pageSize);
	List<BentukUsaha> getByNama(String nama);
	List<BentukUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<BentukUsaha> getByPelakuUsaha(String idKelompokBentukUsaha);
	List<BentukUsaha> getByPelakuUsahaAndPage(String idKelompokBentukUsaha, Integer page, Integer pageSize);
	List<BentukUsaha> getByPelakuUsahaAndNama(String idKelompokBentukUsaha, String nama);
	List<BentukUsaha> getByPelakuUsahaAndNamaAndPage(String idKelompokBentukUsaha, String nama, Integer page, Integer pageSize);
}
