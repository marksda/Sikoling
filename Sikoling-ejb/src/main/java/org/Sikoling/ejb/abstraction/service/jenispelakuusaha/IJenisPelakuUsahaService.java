package org.Sikoling.ejb.abstraction.service.jenispelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;

public interface IJenisPelakuUsahaService {
	JenisPelakuUsaha save(JenisPelakuUsaha t);
	JenisPelakuUsaha update(JenisPelakuUsaha t);
	List<JenisPelakuUsaha> getAll();
	List<JenisPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<JenisPelakuUsaha> getByNama(String nama);
	List<JenisPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
