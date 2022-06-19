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
	public List<Kabupaten> getByNama(String nama) {
		return kabupatenRepositoryJPA.getByNama(nama);
	}

	@Override
	public List<Kabupaten> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kabupatenRepositoryJPA.getByNamaAndPage(nama, page, pageSize);
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
	public List<Kabupaten> getByPropinsiAndNama(String idPropinsi, String nama) {
		return kabupatenRepositoryJPA.getByPropinsiAndNama(idPropinsi, nama);
	}

	@Override
	public List<Kabupaten> getByPropinsiAndNamaAndPage(String idPropinsi, String nama, Integer page,
			Integer pageSize) {
		return kabupatenRepositoryJPA.getByPropinsiAndNamaAndPage(idPropinsi, nama, page, pageSize);
	}

}
