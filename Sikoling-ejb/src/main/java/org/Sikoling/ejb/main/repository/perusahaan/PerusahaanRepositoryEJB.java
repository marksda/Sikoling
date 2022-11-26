package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PerusahaanRepositoryEJB implements IPerusahaanRepository {
	
	@Inject
	private PerusahaanRepositoryJPA perusahaanRepository;

	@Override
	public List<Perusahaan> getAll() {		
		return perusahaanRepository.getAll();
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
	public Perusahaan getByNpwp(String npwp) {
		return perusahaanRepository.getByNpwp(npwp);
	}
	
	@Override
	public Perusahaan updateStatusVerifikasi(Perusahaan t, boolean statusVerifikasi) {
		return perusahaanRepository.updateStatusVerifikasi(t, statusVerifikasi);
	}

	@Override
	public Boolean cekById(String id) {
		return perusahaanRepository.cekById(id);
	}
	
	@Override
	public Perusahaan updateById(String id, Perusahaan perusahaan) {
		return perusahaanRepository.updateById(id, perusahaan);
	}

	
	@Override
	public DeleteResponse delete(String id) {
		return perusahaanRepository.delete(id);
	}

}
