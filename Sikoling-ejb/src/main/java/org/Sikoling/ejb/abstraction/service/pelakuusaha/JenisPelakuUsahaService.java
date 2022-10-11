package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IJenisPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.service.jenispelakuusaha.IJenisPelakuUsahaService;

public class JenisPelakuUsahaService implements IJenisPelakuUsahaService {
	
	private final IJenisPelakuUsahaRepository jenisPelakuUsahaRepository;	

	public JenisPelakuUsahaService(IJenisPelakuUsahaRepository jenisPelakuUsahaRepository) {
		super();
		this.jenisPelakuUsahaRepository = jenisPelakuUsahaRepository;
	}

	@Override
	public JenisPelakuUsaha save(JenisPelakuUsaha t) {
		return jenisPelakuUsahaRepository.save(t);
	}

	@Override
	public JenisPelakuUsaha update(JenisPelakuUsaha t) {
		return jenisPelakuUsahaRepository.update(t);
	}

	@Override
	public List<JenisPelakuUsaha> getAll() {
		return jenisPelakuUsahaRepository.getAll();
	}

	@Override
	public List<JenisPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return jenisPelakuUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<JenisPelakuUsaha> getByNama(String nama) {
		return jenisPelakuUsahaRepository.getByNama(nama);
	}

	@Override
	public List<JenisPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return jenisPelakuUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
