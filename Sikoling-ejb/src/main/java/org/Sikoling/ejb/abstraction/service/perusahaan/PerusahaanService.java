package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;

public class PerusahaanService implements IPerusahaanService {
	
	private final IPerusahaanRepository perusahaanRepository;

	public PerusahaanService(IPerusahaanRepository pemrakarsaRepository) {
		super();
		this.perusahaanRepository = pemrakarsaRepository;
	}

	@Override
	public Perusahaan save(Perusahaan t) {
		return perusahaanRepository.save(t);
	}

	@Override
	public Perusahaan update(Perusahaan t) {
		return perusahaanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return perusahaanRepository.delete(id);
	}

	@Override
	public List<Perusahaan> getAll() {
		return perusahaanRepository.getAll();
	}

	@Override
	public List<Perusahaan> getAllByPage(Integer page, Integer pageSize) {
		return perusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Perusahaan> getByNama(String nama) {
		return perusahaanRepository.getByNama(nama);
	}

	@Override
	public List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return perusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public Boolean cekById(String id) {
		return perusahaanRepository.cekById(id);
	}
	
	@Override
	public Perusahaan getByNpwp(String npwp) {
		return perusahaanRepository.getByNpwp(npwp);
	}

	@Override
	public Perusahaan updateById(String id, Perusahaan perusahaan) {
		return perusahaanRepository.updateById(id, perusahaan);
	}

	
}
