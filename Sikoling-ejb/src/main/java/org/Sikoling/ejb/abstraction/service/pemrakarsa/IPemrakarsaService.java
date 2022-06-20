package org.Sikoling.ejb.abstraction.service.pemrakarsa;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;

public interface IPemrakarsaService {
	Pemrakarsa save(Pemrakarsa t);
	Pemrakarsa update(Pemrakarsa t);
	List<Pemrakarsa> getAll();
	List<Pemrakarsa> getAllByPage(Integer page, Integer pageSize);
	List<Pemrakarsa> getByNama(String nama);
	List<Pemrakarsa> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Pemrakarsa> getByCreator(String idCreator);
	List<Pemrakarsa> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize);
	List<Pemrakarsa> getByCreatorAndNama(String idCreator, String nama);
	List<Pemrakarsa> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize);	
}
