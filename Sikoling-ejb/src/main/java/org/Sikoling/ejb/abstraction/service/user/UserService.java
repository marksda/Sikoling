package org.Sikoling.ejb.abstraction.service.user;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;

public class UserService implements IUserService {
	
	private final IUserRepository userRepository;	

	public UserService(IUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(User u) {		
		return userRepository.save(u);
	}

	@Override
	public User update(User u) {
		return userRepository.update(u);
	}

	@Override
	public List<User> getAllByPage(Integer page, Integer pageSize) {
		return userRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<User> getByQueryNama(String nama) {
		return userRepository.getByQueryNama(nama);
	}

	@Override
	public List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return userRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<User> getAll() {
		return userRepository.getAll();
	}

	
	@Override
	public Boolean cekUserName(String nama) {
		return userRepository.cekUserName(nama);
	}

}
