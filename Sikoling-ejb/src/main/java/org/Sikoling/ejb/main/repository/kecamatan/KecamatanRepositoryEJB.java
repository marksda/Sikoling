package org.Sikoling.ejb.main.repository.kecamatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KecamatanRepositoryEJB implements IKecamatanRepository {
	
	@Inject
	private KecamatanRepositoryJPA kecamatanRepositoryJPA;

	@Override
	public List<Kecamatan> getAll() {
		return kecamatanRepositoryJPA.getAll();
	}

	@Override
	public Kecamatan save(Kecamatan t) {
		return kecamatanRepositoryJPA.save(t);
	}

	@Override
	public Kecamatan update(Kecamatan t) {
		return kecamatanRepositoryJPA.update(t);
	}

	@Override
	public List<Kecamatan> getAllByPage(Integer page, Integer pageSize) {
		return kecamatanRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kecamatan> getByQueryNama(String nama) {
		return kecamatanRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<Kecamatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kecamatanRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupaten(String idKabupaten) {
		return kecamatanRepositoryJPA.getByKabupaten(idKabupaten);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize) {
		return kecamatanRepositoryJPA.getByKabupatenAndPage(idKabupaten, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNama(String idKabupaten, String nama) {
		return kecamatanRepositoryJPA.getByKabupatenAndQueryNama(idKabupaten, nama);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNamaAndPage(String idKabupaten, String nama, Integer page,
			Integer pageSize) {
		return kecamatanRepositoryJPA.getByKabupatenAndQueryNamaAndPage(idKabupaten, nama, page, pageSize);
	}

}
