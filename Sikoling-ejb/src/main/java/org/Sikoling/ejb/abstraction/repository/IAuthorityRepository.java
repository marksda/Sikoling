package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;

public interface IAuthorityRepository extends IRepository<Authority> {	
	DeleteResponse delete(String id);
	List<Authority> getAllByPage(Integer page, Integer pageSize);
	List<Authority> getByNama(String nama);
	List<Authority> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	Authority getByUserName(String userName);
}
