package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Dokumen;

public interface IDokumenService {
	Dokumen save(Dokumen dokumen);
	Dokumen update(Dokumen dokumen);
	List<Dokumen> getAll();
	List<Dokumen> getAllByPage(Integer page, Integer pageSize);
	List<Dokumen> getByNama(String nama);
	List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
