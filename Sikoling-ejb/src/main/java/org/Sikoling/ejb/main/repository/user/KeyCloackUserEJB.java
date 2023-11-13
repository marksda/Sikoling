package org.Sikoling.ejb.main.repository.user;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.IKeyCloackUserRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KeyCloackUserEJB implements IKeyCloackUserRepository{
	
	@Inject
	private KeyCloakUserJPA keyCloakUser;

	@Override
	public User save(User t) throws IOException {
		return keyCloakUser.save(t);
	}

	@Override
	public User update(User t) {
		return keyCloakUser.update(t);
	}

	@Override
	public User delete(User t) throws IOException {
		return keyCloakUser.delete(t);
	}

	@Override
	public List<User> getDaftarData(QueryParamFilters queryParamFilters) {
		return keyCloakUser.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return keyCloakUser.getJumlahData(queryParamFilters);
	}

	@Override
	public User updateSandi(User t) throws IOException {
		return keyCloakUser.updateSandi(t);
	}

	@Override
	public Boolean cekUserName(String nama) {
		return keyCloakUser.cekUserName(nama);
	}

	@Override
	public ResponToken getToken(Credential userAuthenticator) throws IOException {
		return keyCloakUser.getToken(userAuthenticator);
	}

	@Override
	public ResponToken refreshToken(String userName, String refreshToken) throws IOException {
		return keyCloakUser.refreshToken(userName, refreshToken);
	}

	@Override
	public SimpleResponse addRegistrasi(Credential userAuthenticator, Person person) {
		return keyCloakUser.addRegistrasi(userAuthenticator, person);
	}

	
	@Override
	public void deleteSession(String sessionId) {
		keyCloakUser.deleteSession(sessionId);		
	}
	
}
