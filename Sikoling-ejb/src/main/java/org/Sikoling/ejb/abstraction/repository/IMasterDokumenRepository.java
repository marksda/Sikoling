package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public interface IMasterDokumenRepository extends IRepository<Dokumen> {
	DeleteResponse delete(String id);
	Dokumen updateById(String id, Dokumen dokumen);
	List<Dokumen> getAllByPage(Integer page, Integer pageSize);
	List<Dokumen> getByNama(String nama);
	List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
