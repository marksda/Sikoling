package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;

public interface IAuthorityService {
	Authority save(Authority t);
	Authority update(Authority t);
	DeleteResponse delete(String id);
	List<Authority> getAll();
	List<Authority> getAllByPage(Integer page, Integer pageSize);
	List<Authority> getByNama(String nama);
	List<Authority> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	Authority getByUserName(String userName);
}
