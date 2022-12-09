package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autorisasi;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;

public interface IAuthorityRepository extends IRepository<Autorisasi> {	
	DeleteResponse delete(String id);
	List<Autorisasi> getAllByPage(Integer page, Integer pageSize);
	List<Autorisasi> getByNama(String nama);
	List<Autorisasi> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	Autorisasi getByUserName(String userName);
}
