package org.Sikoling.ejb.main.repository.jabatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class JabatanRepositoryEJB implements IJabatanRepository {
	
	@Inject
	private JabatanRepositoryJPA jabatanRepositoryJPA;

	@Override
	public List<Jabatan> getAll() {
		return jabatanRepositoryJPA.getAll();
	}

	@Override
	public Jabatan save(Jabatan t) {
		return jabatanRepositoryJPA.save(t);
	}

	@Override
	public Jabatan update(Jabatan t) {
		return jabatanRepositoryJPA.update(t);
	}

	@Override
	public List<Jabatan> getAllByPage(Integer page, Integer pageSize) {
		return jabatanRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Jabatan> getByQueryNama(String nama) {
		return jabatanRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<Jabatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return jabatanRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
