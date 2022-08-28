package org.Sikoling.ejb.main.repository.user;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;
import org.Sikoling.ejb.main.Infrastructure;
import org.Sikoling.ejb.main.security.user.keycloack.KeyCloakUserRepository;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class UserRepositoryEJB implements IUserRepository {
	
	@Inject
	private KeyCloakUserRepository userRepositoryJPA;

	@Override
	public List<User> getAll() {
		return userRepositoryJPA.getAll();
	}

	@Override
	public User save(User t) {
		return userRepositoryJPA.save(t);
	}

	@Override
	public User update(User t) {
		return userRepositoryJPA.update(t);
	}

	@Override
	public List<User> getAllByPage(Integer page, Integer pageSize) {
		return userRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<User> getByQueryNama(String nama) {
		return userRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return userRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public Boolean cekUserName(String nama) {
		return userRepositoryJPA.cekUserName(nama);
	}

	@Override
	public ResponToken getToken(String nama, String password) {
		return userRepositoryJPA.getToken(nama, password);
	}

}
