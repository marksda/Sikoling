package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPermohonan;

public interface IKategoriPermohonanRepository extends IRepository<KategoriPermohonan> {
	List<KategoriPermohonan> getByNama(String nama);
	DeleteResponse delete(String id);
}
