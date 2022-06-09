package org.Sikoling.ejb.abstraction.service.bentukusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;

public interface IBentukUsahaService {
	BentukUsaha save(BentukUsaha bentukUsaha);
	BentukUsaha update(BentukUsaha bentukUsaha);
	List<BentukUsaha> getAll();
	List<BentukUsaha> getAllByPage(Integer page, Integer pageSize);
	List<BentukUsaha> getByQueryNama(String nama);
	List<BentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);	
}
