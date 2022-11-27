package org.Sikoling.ejb.main.repository.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriPelakuUsahaRepositoryEJB implements IKategoriPelakuUsahaRepository {
	
	@Inject
	private KategoriPelakuUsahaRepositoryJPA kategoriPelakuUsahaRepository;

	@Override
	public List<KategoriPelakuUsaha> getAll() {
		return kategoriPelakuUsahaRepository.getAll();
	}
	
	@Override
	public List<KategoriPelakuUsaha> getALLBySkalaUsaha(String idSkalaUsaha) {
		return kategoriPelakuUsahaRepository.getALLBySkalaUsaha(idSkalaUsaha);
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
	public KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha) {
		return kategoriPelakuUsahaRepository.updateById(id, kategoriPelakuUsaha);
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriPelakuUsahaRepository.delete(id);
	}


}
