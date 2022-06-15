package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;

public interface IBidangUsahaRepository extends IRepository<BidangUsaha> {
	List<BidangUsaha> getAllByPage(Integer page, Integer pageSize);
	List<BidangUsaha> getByNama(String nama);
	List<BidangUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
