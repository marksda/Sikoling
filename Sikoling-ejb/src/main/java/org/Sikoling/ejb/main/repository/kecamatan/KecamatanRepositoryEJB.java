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
	private KecamatanRepositoryJPA kecamatanRepository;

	@Override
	public List<Kecamatan> getAll() {
		return kecamatanRepository.getAll();
	}

	@Override
	public Kecamatan save(Kecamatan t) {
		return kecamatanRepository.save(t);
	}

	@Override
	public Kecamatan update(Kecamatan t) {
		return kecamatanRepository.update(t);
	}

	@Override
	public List<Kecamatan> getAllByPage(Integer page, Integer pageSize) {
		return kecamatanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kecamatan> getByQueryNama(String nama) {
		return kecamatanRepository.getByQueryNama(nama);
	}

	@Override
	public List<Kecamatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kecamatanRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupaten(String idKabupaten) {
		return kecamatanRepository.getByKabupaten(idKabupaten);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndPage(idKabupaten, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNama(String idKabupaten, String nama) {
		return kecamatanRepository.getByKabupatenAndQueryNama(idKabupaten, nama);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNamaAndPage(String idKabupaten, String nama, Integer page,
			Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndQueryNamaAndPage(idKabupaten, nama, page, pageSize);
	}

}
