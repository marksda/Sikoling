package org.Sikoling.ejb.abstraction.service.keycloackuser;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.Credential;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.repository.IKeyCloackUserRepository;

public class KeyCloackUserService implements IKeyCloackUserService {
	
	private final IKeyCloackUserRepository userRepository;	

	public KeyCloackUserService(IKeyCloackUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User t) throws IOException {		
		return userRepository.save(t);
	}

	@Override
	public User update(User t) {
		return userRepository.update(t);
	}

	@Override
	public User updateSandi(User t) throws IOException {
		return userRepository.updateSandi(t);
	}	

	@Override
	public User delete(User t) throws IOException {
		return userRepository.delete(t);
	}	

	@Override
	public List<User> getDaftarData(QueryParamFilters queryParamFilters) {
		return userRepository.getDaftarData(queryParamFilters);
	}	

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return userRepository.getJumlahData(queryParamFilters);
	}
	
	@Override
	public Boolean cekUserName(String nama) {
		return userRepository.cekUserName(nama);
	}

	@Override
	public ResponToken getToken(Credential u) throws IOException {		
		return userRepository.getToken(u);
	}
	
	@Override
	public SimpleResponse addRegistrasi(Credential userAuthenticator, Person person) {
		return userRepository.addRegistrasi(userAuthenticator, person);
	}
	
	@Override
	public ResponToken refreshToken(String userName, String refreshToken) throws IOException {
		return userRepository.refreshToken(userName, refreshToken);
	}

		
}
