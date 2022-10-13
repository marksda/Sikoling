package org.Sikoling.ejb.main.repository.hakakses;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.repository.IHakAksesRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class HakAksesRepositoryEJB implements IHakAksesRepository {
	
	@Inject
	private HakAksesRepositoryJPA hakAksesRepository;
		
	@Override
	public List<HakAkses> getAll() {
		return hakAksesRepository.getAll();
	}

	@Override
	public HakAkses save(HakAkses t) {
		return hakAksesRepository.save(t);
	}

	@Override
	public HakAkses update(HakAkses t) {
		return hakAksesRepository.update(t);
	}

	@Override
	public List<HakAkses> getAllByPage(Integer page, Integer pageSize) {
		return hakAksesRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<HakAkses> getByNama(String nama) {
		return hakAksesRepository.getByNama(nama);
	}

	@Override
	public List<HakAkses> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return hakAksesRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
