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
	public List<BentukUsaha> getByNama(String nama) {
		return bentukUsahaRepository.getByNama(nama);
	}

	@Override
	public List<BentukUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return bentukUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}
	
	@Override
	public List<BentukUsaha> getByPelakuUsaha(String idKelompokBentukUsaha) {
		return bentukUsahaRepository.getByPelakuUsaha(idKelompokBentukUsaha);
	}
	
	@Override
	public List<BentukUsaha> getByPelakuUsahaAndPage(String idKelompokBentukUsaha, Integer page,
			Integer pageSize) {
		return bentukUsahaRepository.getByPelakuUsahaAndPage(idKelompokBentukUsaha, page, pageSize);
	}
	
	@Override
	public List<BentukUsaha> getByPelakuUsahaAndNama(String idKelompokBentukUsaha, String nama) {
		return bentukUsahaRepository.getByPelakuUsahaAndNama(idKelompokBentukUsaha, nama);
	}
	
	@Override
	public List<BentukUsaha> getByPelakuUsahaAndNamaAndPage(String idKelompokBentukUsaha, String nama,
			Integer page, Integer pageSize) {
		return bentukUsahaRepository.getByPelakuUsahaAndNamaAndPage(idKelompokBentukUsaha, nama, page, pageSize);
	}

}
