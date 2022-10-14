package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.service.jenispelakuusaha.IJenisPelakuUsahaService;

public class JenisPelakuUsahaService implements IJenisPelakuUsahaService {
	
	private final IKategoriPelakuUsahaRepository jenisPelakuUsahaRepository;	

	public JenisPelakuUsahaService(IKategoriPelakuUsahaRepository jenisPelakuUsahaRepository) {
		super();
		this.jenisPelakuUsahaRepository = jenisPelakuUsahaRepository;
	}

	@Override
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) {
		return jenisPelakuUsahaRepository.save(t);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		return jenisPelakuUsahaRepository.update(t);
	}

	@Override
	public List<KategoriPelakuUsaha> getAll() {
		return jenisPelakuUsahaRepository.getAll();
	}

	@Override
	public List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return jenisPelakuUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriPelakuUsaha> getByNama(String nama) {
		return jenisPelakuUsahaRepository.getByNama(nama);
	}

	@Override
	public List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return jenisPelakuUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
