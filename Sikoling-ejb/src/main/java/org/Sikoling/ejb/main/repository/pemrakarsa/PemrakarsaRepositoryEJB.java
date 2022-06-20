package org.Sikoling.ejb.main.repository.pemrakarsa;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.repository.IPemrakarsaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PemrakarsaRepositoryEJB implements IPemrakarsaRepository {
	
	@Inject
	private PemrakarsaRepositoryJPA pemrakarsaRepositoryJPA;

	@Override
	public List<Pemrakarsa> getAll() {		
		return pemrakarsaRepositoryJPA.getAll();
	}

	@Override
	public Pemrakarsa save(Pemrakarsa t) {
		return pemrakarsaRepositoryJPA.save(t);
	}

	@Override
	public Pemrakarsa update(Pemrakarsa t) {
		return pemrakarsaRepositoryJPA.update(t);
	}

	@Override
	public List<Pemrakarsa> getAllByPage(Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Pemrakarsa> getByNama(String nama) {
		return pemrakarsaRepositoryJPA.getByNama(nama);
	}

	@Override
	public List<Pemrakarsa> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Pemrakarsa> getByCreator(String idCreator) {
		return pemrakarsaRepositoryJPA.getByCreator(idCreator);
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndPage(String idCreator, Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getByCreatorAndPage(idCreator, page, pageSize);
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndNama(String idCreator, String nama) {
		return pemrakarsaRepositoryJPA.getByCreatorAndNama(idCreator, nama);
	}

	@Override
	public List<Pemrakarsa> getByCreatorAndNamaAndPage(String idCreator, String nama, Integer page, Integer pageSize) {
		return pemrakarsaRepositoryJPA.getByCreatorAndNamaAndPage(idCreator, nama, page, pageSize);
	}

}
