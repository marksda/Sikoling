package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;

public class AuthorityService implements IAuthorityService {
	
	private final IAuthorityRepository authorityRepository;

	public AuthorityService(IAuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Authority save(Authority t) {
		return authorityRepository.save(t);
	}

	@Override
	public Authority update(Authority t) {
		return authorityRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return authorityRepository.delete(id);
	}
	
	@Override
	public List<Authority> getAll() {
		return authorityRepository.getAll();
	}

	@Override
	public List<Authority> getAllByPage(Integer page, Integer pageSize) {
		return authorityRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Authority> getByNama(String nama) {
		return authorityRepository.getByNama(nama);
	}

	@Override
	public List<Authority> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return authorityRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public Authority getByUserName(String userName) {
		return authorityRepository.getByUserName(userName);
	}

	
}
