package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;

public class PelakuUsahaService implements IPelakuUsahaServices {
	
	private final IPelakuUsahaRepository pelakuUsahaRepository;

	public PelakuUsahaService(IPelakuUsahaRepository pelakuUsahaRepository) {
		this.pelakuUsahaRepository = pelakuUsahaRepository;
	}

	@Override
	public List<PelakuUsaha> getALL() {
		return pelakuUsahaRepository.getAll();
	}

	@Override
	public PelakuUsaha save(PelakuUsaha pelakuUsaha) {
		return pelakuUsahaRepository.save(pelakuUsaha);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha pelakuUsaha) {
		return pelakuUsahaRepository.save(pelakuUsaha);
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

	
	@Override
	public PelakuUsaha updateById(String id, PelakuUsaha pelakuUsaha) {
		return pelakuUsahaRepository.updateById(id, pelakuUsaha);
	}

	@Override
	public DeleteResponse delete(String id) {
		return pelakuUsahaRepository.delete(id);
	}

}
