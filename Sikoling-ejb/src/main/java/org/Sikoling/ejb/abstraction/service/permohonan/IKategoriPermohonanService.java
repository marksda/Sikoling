package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;

public interface IKategoriPermohonanService {
	DeleteResponse delete(String id);
	KategoriPermohonan save(KategoriPermohonan t);
	KategoriPermohonan update(KategoriPermohonan t);
	List<KategoriPermohonan> getAll();
	List<KategoriPermohonan> getByNama(String nama);
}
