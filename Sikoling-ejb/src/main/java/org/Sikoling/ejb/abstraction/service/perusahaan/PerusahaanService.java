package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;

public class PerusahaanService implements IPerusahaanService {
	
	private final IPerusahaanRepository pemrakarsaRepository;

	public PerusahaanService(IPerusahaanRepository pemrakarsaRepository) {
		super();
		this.pemrakarsaRepository = pemrakarsaRepository;
	}

	@Override
	public Perusahaan save(Perusahaan t) {
		return pemrakarsaRepository.save(t);
	}

	@Override
	public Perusahaan update(Perusahaan t) {
		return pemrakarsaRepository.update(t);
	}

	@Override
	public List<Perusahaan> getAll() {
		return pemrakarsaRepository.getAll();
	}

	@Override
	public List<Perusahaan> getAllByPage(Integer page, Integer pageSize) {
		return pemrakarsaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Perusahaan> getByNama(String nama) {
		return pemrakarsaRepository.getByNama(nama);
	}

	@Override
	public List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Perusahaan> getByCreator(String idCreator) {
		return pemrakarsaRepository.getByCreator(idCreator);
	}

	@Override
	public List<Perusahaan> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return pemrakarsaRepository.getByCreatorAndPage(idCreator, page, pageSize);
	}

	@Override
	public List<Perusahaan> getByCreatorAndNama(String idCreator, String nama) {
		return null;
	}

	@Override
	public List<Perusahaan> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepository.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize);
	}

	
	@Override
	public Boolean getById(String id) {
		return pemrakarsaRepository.getById(id);
	}

}
