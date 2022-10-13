package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Dokumen;

public interface IDokumenRepository extends IRepository<Dokumen> {
	List<Dokumen> getAllByPage(Integer page, Integer pageSize);
	List<Dokumen> getByNama(String nama);
	List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
