package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;

public interface IDetailPelakuUsahaRepository extends IRepository<PelakuUsaha> {
	List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<PelakuUsaha> getByNama(String nama);
	List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);

}
