package org.Sikoling.ejb.abstraction.service.desa;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;

public class DesaService implements IDesaService {
	
	private final IDesaRepository desaRepository;

	public DesaService(IDesaRepository desaRepository) {
		super();
		this.desaRepository = desaRepository;
	}

	@Override
	public Desa save(Desa desa) {
		return desaRepository.save(desa);
	}

	@Override
	public Desa update(Desa desa) {
		return desaRepository.update(desa);
	}

	@Override
	public List<Desa> getAll() {
		return desaRepository.getAll();
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
