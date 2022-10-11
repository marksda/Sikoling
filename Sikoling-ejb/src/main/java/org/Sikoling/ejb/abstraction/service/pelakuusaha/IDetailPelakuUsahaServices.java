package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;

public interface IDetailPelakuUsahaServices {
	List<DetailPelakuUsaha> getALL();
	DetailPelakuUsaha save(DetailPelakuUsaha detailPelakuUsaha);
	DetailPelakuUsaha update(DetailPelakuUsaha detailPelakuUsaha);
	List<DetailPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<DetailPelakuUsaha> getByNama(String nama);
	List<DetailPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
