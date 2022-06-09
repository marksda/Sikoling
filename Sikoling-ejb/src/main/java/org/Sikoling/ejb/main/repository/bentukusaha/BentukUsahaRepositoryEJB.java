package org.Sikoling.ejb.main.repository.bentukusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.repository.IBentukUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class BentukUsahaRepositoryEJB implements IBentukUsahaRepository {
	
	@Inject
	private BentukUsahaRepositoryJPA bentukUsahaRepository;

	@Override
	public List<BentukUsaha> getAll() {
		return bentukUsahaRepository.getAll();
	}

	@Override
	public BentukUsaha save(BentukUsaha t) {
		return bentukUsahaRepository.save(t);
	}

	@Override
	public BentukUsaha update(BentukUsaha t) {
		return bentukUsahaRepository.update(t);
	}

	@Override
	public List<BentukUsaha> getAllByPage(Integer page, Integer pageSize) {
		return bentukUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<BentukUsaha> getByQueryNama(String nama) {
		return bentukUsahaRepository.getByQueryNama(nama);
	}

	@Override
	public List<BentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return bentukUsahaRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
