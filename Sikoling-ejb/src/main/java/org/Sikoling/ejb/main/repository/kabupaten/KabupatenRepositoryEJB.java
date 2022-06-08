package org.Sikoling.ejb.main.repository.kabupaten;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KabupatenRepositoryEJB implements IKabupatenRepository {
	
	@Inject
	private KabupatenRepositoryJPA kabupatenRepositoryJPA;

	@Override
	public List<Kabupaten> getAll() {
		return kabupatenRepositoryJPA.getAll();
	}

	@Override
	public Kabupaten save(Kabupaten t) {
		return kabupatenRepositoryJPA.save(t);
	}

	@Override
	public Kabupaten update(Kabupaten t) {
		return kabupatenRepositoryJPA.update(t);
	}

	@Override
	public List<Kabupaten> getAllByPage(Integer page, Integer pageSize) {
		return kabupatenRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kabupaten> getByQueryNama(String nama) {
		return kabupatenRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<Kabupaten> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kabupatenRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kabupaten> getByPropinsi(String idPropinsi) {
		return kabupatenRepositoryJPA.getByPropinsi(idPropinsi);
	}

	@Override
	public List<Kabupaten> getByPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize) {
		return kabupatenRepositoryJPA.getByPropinsiAndPage(idPropinsi, page, pageSize);
	}

	@Override
	public List<Kabupaten> getByPropinsiAndQueryNama(String idPropinsi, String nama) {
		return kabupatenRepositoryJPA.getByPropinsiAndQueryNama(idPropinsi, nama);
	}

	@Override
	public List<Kabupaten> getByPropinsiAndQueryNamaAndPage(String idPropinsi, String nama, Integer page,
			Integer pageSize) {
		return kabupatenRepositoryJPA.getByPropinsiAndQueryNamaAndPage(idPropinsi, nama, page, pageSize);
	}

}
