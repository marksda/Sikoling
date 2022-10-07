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
public class PemrakarsaRepositoryEJB implements IPerusahaanRepository {
	
	@Inject
	private PemrakarsaRepositoryJPA pemrakarsaRepositoryJPA;

	@Override
	public List<Perusahaan> getAll() {		
		return pemrakarsaRepositoryJPA.getAll();
	}

	@Override
	public Perusahaan save(Perusahaan t) {
		return pemrakarsaRepositoryJPA.save(t);
	}

	@Override
	public Perusahaan update(Perusahaan t) {
		return pemrakarsaRepositoryJPA.update(t);
	}

	@Override
	public List<Perusahaan> getAllByPage(Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Perusahaan> getByNama(String nama) {
		return pemrakarsaRepositoryJPA.getByNama(nama);
	}

	@Override
	public List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Perusahaan> getByCreator(String idCreator) {
		return pemrakarsaRepositoryJPA.getByCreator(idCreator);
	}

	@Override
	public List<Perusahaan> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getByCreatorAndPage(idCreator, page, pageSize);
	}

	@Override
	public List<Perusahaan> getByCreatorAndNama(String idCreator, String nama) {
		return pemrakarsaRepositoryJPA.getByCreatorAndNama(idCreator, nama);
	}

	@Override
	public List<Perusahaan> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize);
	}

}
