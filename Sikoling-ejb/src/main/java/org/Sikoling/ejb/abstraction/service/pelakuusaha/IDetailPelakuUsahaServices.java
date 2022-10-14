package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;

public interface IDetailPelakuUsahaServices {
	List<PelakuUsaha> getALL();
	PelakuUsaha save(PelakuUsaha detailPelakuUsaha);
	PelakuUsaha update(PelakuUsaha detailPelakuUsaha);
	List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<PelakuUsaha> getByNama(String nama);
	List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
