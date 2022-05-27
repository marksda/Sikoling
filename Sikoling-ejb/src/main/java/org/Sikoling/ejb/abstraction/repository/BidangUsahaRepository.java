package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;

public interface BidangUsahaRepository extends Repository<BidangUsaha> {
	List<BidangUsaha> getAll(Integer page, Integer pageSize);
	List<BidangUsaha> getByQueryNama(String nama);
}
