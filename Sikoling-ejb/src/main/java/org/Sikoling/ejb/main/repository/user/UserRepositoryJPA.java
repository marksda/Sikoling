package org.Sikoling.ejb.main.repository.user;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.ResponToken;
import org.Sikoling.ejb.abstraction.entity.SimpleResponse;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.UserAuthenticator;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;
import jakarta.persistence.EntityManager;

public class UserRepositoryJPA implements IUserRepository {
	
	private final EntityManager entityManager;	

	public UserRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<User> getAll() {
		return entityManager.createNamedQuery("UserData.findAll", UserData.class)
				.getResultList()
				.stream()
				.map(t -> convertUserDataToUser(t))
				.collect(Collectors.toList());
	}

	@Override
	public User save(User t) {
		UserData userData = convertUserToUserData(t);
		entityManager.persist(userData);
		entityManager.flush();
		return convertUserDataToUser(userData);
	}

	@Override
	public User update(User t) {
		UserData userData = convertUserToUserData(t);
		userData = entityManager.merge(userData);
		return convertUserDataToUser(userData);
	}

	@Override
	public List<User> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("UserData.findAll", UserData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertUserDataToUser(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getByQueryNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertUserDataToUser(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("UserData.findByQueryNama", UserData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertUserDataToUser(t))
				.collect(Collectors.toList());
	}
		
	private UserData convertUserToUserData(User u) {
		UserData userData = new UserData();
		userData.setId(u.getId());
		userData.setUser(u.getUserName());
		userData.setPassword(u.getPassword());
		userData.setStatusInternal(u.getStatusInternal());
		userData.setStatusLogin(u.getLoginStatus());
		userData.setTanggalRegistrasi(u.getRegisterDate());
		
		return userData;
	}
	
	private User convertUserDataToUser(UserData d) {
		return new User(d.getId(), d.getUser(), d.getPassword(), d.getStatusLogin(), d.getTanggalRegistrasi(), d.getStatusInternal(), null, null);
	}
	
	@Override
	public Boolean cekUserName(String nama) {
		return null;
	}

	@Override
	public ResponToken getToken(UserAuthenticator u) {
		return null;
	}
	
	@Override
	public SimpleResponse addRegistrasi(UserAuthenticator userAuthenticator, Person person) {
		return null;
	}

	
	@Override
	public ResponToken refreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
