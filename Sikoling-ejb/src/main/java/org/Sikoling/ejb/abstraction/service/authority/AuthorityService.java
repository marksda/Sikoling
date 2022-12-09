package org.Sikoling.ejb.abstraction.service.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autorisasi;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;

public class AuthorityService implements IAuthorityService {
	
	private final IAuthorityRepository authorityRepository;

	public AuthorityService(IAuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Autorisasi save(Autorisasi t) {
		return authorityRepository.save(t);
	}

	@Override
	public Autorisasi update(Autorisasi t) {
		return authorityRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return authorityRepository.delete(id);
	}

	@Override
	public List<Autorisasi> getAllByPage(Integer page, Integer pageSize) {
		return authorityRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Autorisasi> getByNama(String nama) {
		return authorityRepository.getByNama(nama);
	}

	@Override
	public List<Autorisasi> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return authorityRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public Autorisasi getByUserName(String userName) {
		return authorityRepository.getByUserName(userName);
	}

}
