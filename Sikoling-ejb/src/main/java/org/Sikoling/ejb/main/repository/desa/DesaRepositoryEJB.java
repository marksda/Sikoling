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
public class DesaRepositoryEJB implements IDesaRepository {
	
	@Inject
	private DesaRepositoryJPA desaRepository;

	@Override
	public List<Desa> getAll() {
		return desaRepository.getAll();
	}

	@Override
	public Desa save(Desa t) {
		return desaRepository.save(t);
	}

	@Override
	public Desa update(Desa t) {
		return desaRepository.update(t);
	}

	@Override
	public List<Desa> getAllByPage(Integer page, Integer pageSize) {
		return desaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Desa> getByQueryNama(String nama) {
		return desaRepository.getByQueryNama(nama);
	}

	@Override
	public List<Desa> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return desaRepository.getByQueryNamaAndPage(nama, page, pageSize);
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
	public List<Desa> getByKecamatanAndQueryNama(String idKecamatan, String nama) {
		return desaRepository.getByKecamatanAndQueryNama(idKecamatan, nama);
	}

	@Override
	public List<Desa> getByKecamatanAndQueryNamaAndPage(String idKecamatan, String nama, Integer page,
			Integer pageSize) {
		return desaRepository.getByKecamatanAndQueryNamaAndPage(idKecamatan, nama, page, pageSize);
	}

}
