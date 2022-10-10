package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IJenisPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class JenisPelakuUsahaRepositoryEJB implements IJenisPelakuUsahaRepository {
	
	@Inject
	private JenisPelakuUsahaRepositoryJPA repositoryJPA;

	@Override
	public List<JenisPelakuUsaha> getAll() {
		return repositoryJPA.getAll();
	}

	@Override
	public JenisPelakuUsaha save(JenisPelakuUsaha t) {
		return repositoryJPA.save(t);
	}

	@Override
	public JenisPelakuUsaha update(JenisPelakuUsaha t) {
		return repositoryJPA.update(t);
	}

	@Override
	public List<JenisPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return repositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<JenisPelakuUsaha> getByNama(String nama) {
		return repositoryJPA.getByNama(nama);
	}

	@Override
	public List<JenisPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return repositoryJPA.getByNamaAndPage(nama, page, pageSize);
	}

}
