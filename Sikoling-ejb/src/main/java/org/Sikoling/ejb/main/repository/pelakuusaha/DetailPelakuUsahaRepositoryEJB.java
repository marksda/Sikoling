package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IDetailPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DetailPelakuUsahaRepositoryEJB implements IDetailPelakuUsahaRepository {
	
	@Inject
	private DetailPelakuUsahaRepositoryJPA repositoryJPA;

	@Override
	public List<PelakuUsaha> getAll() {
		return repositoryJPA.getAll();
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) {
		return repositoryJPA.save(t);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		return repositoryJPA.update(t);
	}

	@Override
	public List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return repositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<PelakuUsaha> getByNama(String nama) {
		return repositoryJPA.getByNama(nama);
	}

	@Override
	public List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return repositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

}
