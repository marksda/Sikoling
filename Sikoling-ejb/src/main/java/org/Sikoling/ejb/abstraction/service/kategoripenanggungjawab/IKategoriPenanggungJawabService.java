package org.Sikoling.ejb.abstraction.service.kategoripenanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab;

public interface IKategoriPenanggungJawabService {
	KategoriPenanggungJawab save(KategoriPenanggungJawab t);
	KategoriPenanggungJawab update(KategoriPenanggungJawab t);
	List<KategoriPenanggungJawab> getAllByPage(Integer page, Integer pageSize);
	List<KategoriPenanggungJawab> getByQueryNama(String nama);
	List<KategoriPenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
