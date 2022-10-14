package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class PelakuUsahaRepositoryEJB implements IPelakuUsahaRepository {
	
	@Inject
	private PelakuUsahaRepositoryJPA pelakuUsahaRepository;

	@Override
	public List<PelakuUsaha> getAll() {
		return pelakuUsahaRepository.getAll();
	}

	@Override
	public PelakuUsaha save(PelakuUsaha t) {
		return pelakuUsahaRepository.save(t);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha t) {
		return pelakuUsahaRepository.update(t);
	}

	@Override
	public List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return pelakuUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<PelakuUsaha> getByNama(String nama) {
		return pelakuUsahaRepository.getByNama(nama);
	}

	@Override
	public List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return pelakuUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<PelakuUsaha> getByKategoriPelakuUsaha(KategoriPelakuUsaha kategoriPelakuUsaha) {
		return pelakuUsahaRepository.getByKategoriPelakuUsaha(kategoriPelakuUsaha);
	}

	@Override
	public List<PelakuUsaha> getByKategoriPelakuUsahaAndPage(KategoriPelakuUsaha kategoriPelakuUsaha, Integer page,
			Integer pageSize) {
		return pelakuUsahaRepository.getByKategoriPelakuUsahaAndPage(kategoriPelakuUsaha, page, pageSize);
	}

}
