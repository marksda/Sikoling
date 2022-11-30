package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.String;

public interface IMasterDokumenService {
	String save(String dokumen);
	String update(String dokumen);
	String updateById(String id, String dokumen);
	DeleteResponse delete(String Id);
	List<String> getAll();
	List<String> getAllByPage(Integer page, Integer pageSize);
	List<String> getByNama(String nama);
	List<String> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
