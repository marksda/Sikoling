package org.Sikoling.ejb.main.security.user.keycloack;

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

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class KeyCloackUserEJB implements IKeyCloackUserRepository{
	@Inject
	private KeyCloakUserJPA userRepositoryJPA;

	@Override
	public User save(User t) throws IOException {
		return null;
	}

	@Override
	public User update(User t) {
		return null;
	}

	@Override
	public User delete(User t) throws IOException {
		return null;
	}

	@Override
	public List<User> getDaftarData(QueryParamFilters queryParamFilters) {
		return null;
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return null;
	}

	@Override
	public User updateSandi(String sandiLama, User t) throws IOException {
		return null;
	}

	@Override
	public Boolean cekUserName(String nama) {
		return null;
	}

	@Override
	public ResponToken getToken(Credential userAuthenticator) {
		return null;
	}

	@Override
	public ResponToken refreshToken(String refreshToken) {
		return null;
	}

	@Override
	public SimpleResponse addRegistrasi(Credential userAuthenticator, Person person) {
		return null;
	}
	
	
}
