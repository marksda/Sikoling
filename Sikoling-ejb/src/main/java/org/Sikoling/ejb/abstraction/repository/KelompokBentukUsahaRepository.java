package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.KelompokBentukUsaha;

public interface KelompokBentukUsahaRepository extends Repository<KelompokBentukUsaha> {
	List<KelompokBentukUsaha> getAll(Integer page, Integer pageSize);
	List<KelompokBentukUsaha> getByQueryNama(String nama);
}
