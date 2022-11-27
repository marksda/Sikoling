package org.Sikoling.ejb.abstraction.service.skalausaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;

public interface ISkalaUsahaService {
	List<SkalaUsaha> getALL();
	SkalaUsaha save(SkalaUsaha skalaUsaha);
	SkalaUsaha update(SkalaUsaha skalaUsaha);
	SkalaUsaha updateById(String id, SkalaUsaha skalaUsaha);
	DeleteResponse delete(String id);
	List<SkalaUsaha> getAllByPage(Integer page, Integer pageSize);
	List<SkalaUsaha> getByNama(String nama);
	List<SkalaUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize);
}
