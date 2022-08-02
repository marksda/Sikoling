package org.Sikoling.ejb.main.security.user.keycloack;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;
import org.keycloak.admin.client.Keycloak;

public class KeyCloakUserRepository implements IUserRepository {
	
	private final Keycloak keyCloak;
	private final String realm;	

	public KeyCloakUserRepository(Keycloak keyCloak, String realm) {
		super();
		this.keyCloak = keyCloak;
		this.realm = realm;
	}
	

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByQueryNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
