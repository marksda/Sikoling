package org.Sikoling.ejb.main.repository.authority;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Autorisasi;
import org.Sikoling.ejb.abstraction.repository.IAutorisasiRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class AutorisasiRepositoryEJB implements IAutorisasiRepository {
	
	@Inject
	private AutorisasiRepositoryJPA autorisasiRepository;

	@Override
	public List<Autorisasi> getAll() {
		return autorisasiRepository.getAll();
	}

	@Override
	public Autorisasi save(Autorisasi t) {
		return autorisasiRepository.save(t);
	}

	@Override
	public Autorisasi update(Autorisasi t) {
		return autorisasiRepository.update(t);
	}

	@Override
	public List<Autorisasi> getAllByPage(Integer page, Integer pageSize) {
		return autorisasiRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Autorisasi> getByNama(String nama) {
		return autorisasiRepository.getByNama(nama);
	}

	@Override
	public List<Autorisasi> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return autorisasiRepository.getByNamaAndPage(nama, page, pageSize);
	}

}