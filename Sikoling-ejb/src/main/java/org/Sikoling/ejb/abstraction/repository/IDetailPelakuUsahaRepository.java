package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;

public interface IDetailPelakuUsahaRepository extends IRepository<DetailPelakuUsaha> {
	List<DetailPelakuUsaha> getAllByPage(Integer page, Integer pageSize);
	List<DetailPelakuUsaha> getByNama(String nama);
	List<DetailPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);

}
