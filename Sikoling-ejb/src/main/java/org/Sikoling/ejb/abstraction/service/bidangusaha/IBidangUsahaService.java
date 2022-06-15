package org.Sikoling.ejb.abstraction.service.bidangusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;

public interface IBidangUsahaService {
	BidangUsaha save(BidangUsaha bidangUsaha);
	BidangUsaha update(BidangUsaha bidangUsaha);
	List<BidangUsaha> getAll();
	List<BidangUsaha> getAllByPage(Integer page, Integer pageSize);
	List<BidangUsaha> getByNama(String nama);
	List<BidangUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
