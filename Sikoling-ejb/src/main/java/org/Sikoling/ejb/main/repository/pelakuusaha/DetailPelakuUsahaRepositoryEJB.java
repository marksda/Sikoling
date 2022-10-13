package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IDetailPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DetailPelakuUsahaRepositoryEJB implements IDetailPelakuUsahaRepository<DetailPelakuUsaha> {
	
	@Inject
	private DetailPelakuUsahaRepositoryJPA repositoryJPA;

	@Override
	public List<DetailPelakuUsaha> getAll() {
		return repositoryJPA.getAll();
	}

	@Override
	public DetailPelakuUsaha save(DetailPelakuUsaha t) {
		return repositoryJPA.save(t);
	}

	@Override
	public DetailPelakuUsaha update(DetailPelakuUsaha t) {
		return repositoryJPA.update(t);
	}

	@Override
	public List<DetailPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return repositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<DetailPelakuUsaha> getByNama(String nama) {
		return repositoryJPA.getByNama(nama);
	}

	@Override
	public List<DetailPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return repositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

}
