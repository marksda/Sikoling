package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;

public interface IKeyCloackUserRepository extends IRepository<User> {
	User updateSandi(String sandiLama, User t) throws IOException;
	Boolean cekUserName(String nama);
	ResponToken getToken(Credential userAuthenticator) throws IOException;
	ResponToken refreshToken(String userName, String refreshToken) throws IOException;
	SimpleResponse addRegistrasi(Credential userAuthenticator, Person person);	
}
