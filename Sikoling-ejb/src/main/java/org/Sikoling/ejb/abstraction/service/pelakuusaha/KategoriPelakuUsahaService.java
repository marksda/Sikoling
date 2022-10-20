package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;

public class KategoriPelakuUsahaService implements IKategoriPelakuUsahaServices {
	
	private final IKategoriPelakuUsahaRepository kategoriPelakuUsahaRepository;	

	public KategoriPelakuUsahaService(IKategoriPelakuUsahaRepository kategoriPelakuUsahaRepository) {
		this.kategoriPelakuUsahaRepository = kategoriPelakuUsahaRepository;
	}

	@Override
	public KategoriPelakuUsaha save(KategoriPelakuUsaha t) {
		return kategoriPelakuUsahaRepository.save(t);
	}

	@Override
	public KategoriPelakuUsaha update(KategoriPelakuUsaha t) {
		return kategoriPelakuUsahaRepository.update(t);
	}

	@Override
	public List<KategoriPelakuUsaha> getALL() {
		return kategoriPelakuUsahaRepository.getAll();
	}

	@Override
	public List<KategoriPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return kategoriPelakuUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriPelakuUsaha> getByNama(String nama) {
		return kategoriPelakuUsahaRepository.getByNama(nama);
	}

	@Override
	public List<KategoriPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kategoriPelakuUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}
	
	@Override
	public List<KategoriPelakuUsaha> getALLBySkalaUsaha(String idSkalaUsaha) {
		return kategoriPelakuUsahaRepository.getALLBySkalaUsaha(idSkalaUsaha);
	}

	

}
