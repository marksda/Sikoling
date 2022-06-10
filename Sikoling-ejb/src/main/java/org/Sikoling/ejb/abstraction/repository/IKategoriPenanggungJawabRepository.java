package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab;

public interface IKategoriPenanggungJawabRepository extends IRepository<KategoriPenanggungJawab> {
	List<KategoriPenanggungJawab> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPenanggungJawab> getByQueryNama(String nama);
	List<KategoriPenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
