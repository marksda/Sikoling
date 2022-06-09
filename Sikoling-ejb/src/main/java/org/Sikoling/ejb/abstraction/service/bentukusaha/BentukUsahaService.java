package org.Sikoling.ejb.abstraction.service.bentukusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.repository.IBentukUsahaRepository;

public class BentukUsahaService implements IBentukUsahaService {
	
	private final IBentukUsahaRepository bentukUsahaRepository;	

	public BentukUsahaService(IBentukUsahaRepository bentukUsahaRepository) {
		this.bentukUsahaRepository = bentukUsahaRepository;
	}

	@Override
	public BentukUsaha save(BentukUsaha bentukUsaha) {
		return bentukUsahaRepository.save(bentukUsaha);
	}

	@Override
	public BentukUsaha update(BentukUsaha bentukUsaha) {
		return bentukUsahaRepository.update(bentukUsaha);
	}

	@Override
	public List<BentukUsaha> getAll() {
		return bentukUsahaRepository.getAll();
	}

	@Override
	public List<BentukUsaha> getAllByPage(Integer page, Integer pageSize) {
		return bentukUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<BentukUsaha> getByQueryNama(String nama) {
		return bentukUsahaRepository.getByQueryNama(nama);
	}

	@Override
	public List<BentukUsaha> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return bentukUsahaRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public List<BentukUsaha> getByKelompokBentukUsaha(String idKelompokBentukUsaha) {
		return bentukUsahaRepository.getByKelompokBentukUsaha(idKelompokBentukUsaha);
	}
	

	@Override
	public List<BentukUsaha> getByKelompokBentukUsahaAndPage(String idKelompokBentukUsaha, Integer page,
			Integer pageSize) {
		return bentukUsahaRepository.getByKelompokBentukUsahaAndPage(idKelompokBentukUsaha, page, pageSize);
	}
	

	@Override
	public List<BentukUsaha> getByKelompokBentukUsahaAndNama(String idKelompokBentukUsaha, String nama) {
		return bentukUsahaRepository.getByKelompokBentukUsahaAndNama(idKelompokBentukUsaha, nama);
	}
	

	@Override
	public List<BentukUsaha> getByKelompokBentukUsahaAndNamaAndPage(String idKelompokBentukUsaha, String nama,
			Integer page, Integer pageSize) {
		return bentukUsahaRepository.getByKelompokBentukUsahaAndNamaAndPage(idKelompokBentukUsaha, nama, page, pageSize);
	}

}
