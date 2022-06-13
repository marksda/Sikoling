package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KelompokBentukUsaha;

public interface IKelompokBentukUsahaRepository extends IRepository<KelompokBentukUsaha> {
	List<KelompokBentukUsaha> getAllByPage(Integer page, Integer pageSize);
	List<KelompokBentukUsaha> getByQueryNama(String nama);
	List<KelompokBentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
