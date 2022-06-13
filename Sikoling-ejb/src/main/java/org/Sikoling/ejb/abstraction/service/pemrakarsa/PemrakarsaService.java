package org.Sikoling.ejb.abstraction.service.pemrakarsa;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.repository.IPemrakarsaRepository;

public class PemrakarsaService implements IPemrakarsaService {
	
	private final IPemrakarsaRepository pemrakarsaRepository;

	public PemrakarsaService(IPemrakarsaRepository pemrakarsaRepository) {
		super();
		this.pemrakarsaRepository = pemrakarsaRepository;
	}

	@Override
	public Pemrakarsa save(Pemrakarsa t) {
		return pemrakarsaRepository.save(t);
	}

	@Override
	public Pemrakarsa update(Pemrakarsa t) {
		return pemrakarsaRepository.update(t);
	}

	@Override
	public List<Pemrakarsa> getAll() {
		return pemrakarsaRepository.getAll();
	}

	@Override
	public List<Pemrakarsa> getAllByPage(Integer page, Integer pageSize) {
		return pemrakarsaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Pemrakarsa> getByQueryNama(String nama) {
		return pemrakarsaRepository.getByQueryNama(nama);
	}

	@Override
	public List<Pemrakarsa> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Pemrakarsa> getByCreator(String idCreator) {
		return pemrakarsaRepository.getByCreator(idCreator);
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return pemrakarsaRepository.getByCreatorAndPage(idCreator, page, pageSize);
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndNama(String idCreator, String nama) {
		return null;
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepository.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize);
	}

}
