package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;

public interface IJenisPelakuUsahaServices {
	List<JenisPelakuUsaha> getALL();
	JenisPelakuUsaha save(JenisPelakuUsaha jenisPelakuUsaha);
	JenisPelakuUsaha update(JenisPelakuUsaha jenisPelakuUsaha);
	List<JenisPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<JenisPelakuUsaha> getByNama(String nama);
	List<JenisPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
