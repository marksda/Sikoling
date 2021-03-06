package org.Sikoling.ejb.abstraction.service.user;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.User;

public interface IUserService {
	User save(User u);
	User update(User u);
	List<User> getAll();
	List<User> getAllByPage(Integer page, Integer pageSize);
	List<User> getByQueryNama(String nama);
	List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
