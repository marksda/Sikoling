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
	public Kecamatan save(Kecamatan t, String s) {
		return kecamatanRepository.save(t, s);
	}

	@Override
	public Kecamatan update(Kecamatan t, String s) {
		return kecamatanRepository.update(t, s);
	}

	@Override
	public List<Kecamatan> getAllByPage(Integer page, Integer pageSize) {
		return kecamatanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kecamatan> getByNama(String nama) {
		return kecamatanRepository.getByNama(nama);
	}

	@Override
	public List<Kecamatan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kecamatanRepository.getByNamaAndPage(nama, page, pageSize);
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
	public List<Kecamatan> getByKabupatenAndNama(String idKabupaten, String nama) {
		return kecamatanRepository.getByKabupatenAndNama(idKabupaten, nama);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndNamaAndPage(String idKabupaten, String nama, Integer page,
			Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndNamaAndPage(idKabupaten, nama, page, pageSize);
	}

}
