package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.String;

public interface IMasterDokumenRepository extends IRepository<String> {
	DeleteResponse delete(String id);
	String updateById(String id, String dokumen);
	List<String> getAllByPage(Integer page, Integer pageSize);
	List<String> getByNama(String nama);
	List<String> getByNamaAndPage(String nama, Integer page, Integer pageSize);	
}
