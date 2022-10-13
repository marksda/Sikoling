package org.Sikoling.ejb.main.repository.desa;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DesaRepositoryEJB implements IDesaRepository<Desa, String> {
	
	@Inject
	private DesaRepositoryJPA desaRepository;

	@Override
	public List<Desa> getAll() {
		return desaRepository.getAll();
	}

	@Override
	public Desa save(Desa t, String s) {
		return desaRepository.save(t, s);
	}

	@Override
	public Desa update(Desa t, String s) {
		return desaRepository.update(t, s);
	}

	@Override
	public List<Desa> getAllByPage(Integer page, Integer pageSize) {
		return desaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Desa> getByNama(String nama) {
		return desaRepository.getByNama(nama);
	}

	@Override
	public List<Desa> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return desaRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Desa> getByKecamatan(String idKecamatan) {
		return desaRepository.getByKecamatan(idKecamatan);
	}

	@Override
	public List<Desa> getByKecamatanAndPage(String idKecamatan, Integer page, Integer pageSize) {
		return desaRepository.getByKecamatanAndPage(idKecamatan, page, pageSize);
	}

	@Override
	public List<Desa> getByKecamatanAndNama(String idKecamatan, String nama) {
		return desaRepository.getByKecamatanAndNama(idKecamatan, nama);
	}

	@Override
	public List<Desa> getByKecamatanAndNamaAndPage(String idKecamatan, String nama, Integer page,
			Integer pageSize) {
		return desaRepository.getByKecamatanAndNamaAndPage(idKecamatan, nama, page, pageSize);
	}

}
