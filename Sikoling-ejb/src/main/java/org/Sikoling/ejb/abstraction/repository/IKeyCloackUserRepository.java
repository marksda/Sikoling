package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;

public interface IKeyCloackUserRepository extends IRepository<User> {
//	List<User> getAllByPage(Integer page, Integer pageSize);
//	List<User> getByQueryNama(String nama);
//	List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
	User updateSandi(String sandiLama, User t) throws IOException;
	Boolean cekUserName(String nama);
	ResponToken getToken(Credential userAuthenticator);
	ResponToken refreshToken(String refreshToken);
	SimpleResponse addRegistrasi(Credential userAuthenticator, Person person);
}
