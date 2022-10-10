package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PerusahaanRepositoryEJB implements IPerusahaanRepository {
	
	@Inject
	private PerusahaanRepositoryJPA perusahaanRepository;

	@Override
	public List<Perusahaan> getAll() {		
		return perusahaanRepository.getAll();
	}

	@Override
	public Perusahaan save(Perusahaan t) {
		return perusahaanRepository.save(t);
	}

	@Override
	public Perusahaan update(Perusahaan t) {
		return perusahaanRepository.update(t);
	}

	@Override
	public List<Perusahaan> getAllByPage(Integer page, Integer pageSize) {
		return perusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Perusahaan> getByNama(String nama) {
		return perusahaanRepository.getByNama(nama);
	}

	@Override
	public List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return perusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Perusahaan> getByCreator(String idCreator) {
		return perusahaanRepository.getByCreator(idCreator);
	}

	@Override
	public List<Perusahaan> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return perusahaanRepository.getByCreatorAndPage(idCreator, page, pageSize);
	}

	@Override
	public List<Perusahaan> getByCreatorAndNama(String idCreator, String nama) {
		return perusahaanRepository.getByCreatorAndNama(idCreator, nama);
	}

	@Override
	public List<Perusahaan> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return perusahaanRepository.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize);
	}

}
