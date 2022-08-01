package org.Sikoling.ejb.main.security.sex.keycloack;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;
import org.keycloak.admin.client.Keycloak;

public class KeyCloakJenisKelaminRepository implements IJenisKelaminRepository {
	
	private final Keycloak keyCloak;
	private final String realm;
	

	public KeyCloakJenisKelaminRepository(Keycloak keyCloak, String realm) {
		super();
		this.keyCloak = keyCloak;
		this.realm = realm;
	}

	@Override
	public List<JenisKelamin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JenisKelamin save(JenisKelamin t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JenisKelamin update(JenisKelamin t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JenisKelamin> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JenisKelamin> getByQueryNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JenisKelamin> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
