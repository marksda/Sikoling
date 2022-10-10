package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;
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
	private DetailPelakuUsahaJPA repositoryJPA;

	@Override
	public List<DetailPelakuUsaha> getAll() {
		return repositoryJPA.getAll();
	}

	@Override
	public DetailPelakuUsaha save(DetailPelakuUsaha t, JenisPelakuUsaha s) {
		return repositoryJPA.save(t, s);
	}

	@Override
	public DetailPelakuUsaha update(DetailPelakuUsaha t, JenisPelakuUsaha s) {
		return repositoryJPA.update(t, s);
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
