package org.Sikoling.ejb.main.repository.propinsi;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class PropinsiRepositoryEJB implements IPropinsiRepository {
	
	@Inject
	private PropinsiRepositoryJPA propinsiRepositoryJPA;

	@Override
	public List<Propinsi> getAll() {
		return propinsiRepositoryJPA.getAll();
	}

	@Override
	public Propinsi save(Propinsi t) {
		return propinsiRepositoryJPA.save(t);
	}

	@Override
	public Propinsi update(Propinsi t) {
		return propinsiRepositoryJPA.update(t);
	}

	@Override
	public List<Propinsi> getAllByPage(Integer page, Integer pageSize) {
		return propinsiRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Propinsi> getByQueryNama(String nama) {
		return propinsiRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<Propinsi> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return propinsiRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
