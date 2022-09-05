package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.UserAuthenticator;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.ResponToken;

public interface IUserRepository extends IRepository<User> {
	List<User> getAllByPage(Integer page, Integer pageSize);
	List<User> getByQueryNama(String nama);
	List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
	Boolean cekUserName(String nama);
	ResponToken getToken(UserAuthenticator userAuthenticator);
	Boolean addRegistrasi(UserAuthenticator userAuthenticator, Person person);
}
