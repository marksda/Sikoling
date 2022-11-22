package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Kbli;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KbliRepositoryEJB implements IKbliRepository {
	
	@Inject
	private KbliRepositoryJPA kbliRepository;
	
	@Override
	public List<Kbli> getAll() {
		return kbliRepository.getAll();
	}

	@Override
	public Kbli save(Kbli t) {
		return kbliRepository.save(t);
	}

	@Override
	public Kbli update(Kbli t) {
		return kbliRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String kode) {
		return kbliRepository.delete(kode);
	}

	@Override
	public Kbli updateById(String id, Kbli kbli) {
		return kbliRepository.updateById(id, kbli);
	}

	@Override
	public List<Kbli> getAllByPage(Integer page, Integer pageSize) {
		return kbliRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kbli> getByNama(String nama) {
		return kbliRepository.getByNama(nama);
	}

	@Override
	public List<Kbli> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kbliRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kbli> getByKode(String kode) {
		return kbliRepository.getByKode(kode);
	}

	@Override
	public List<Kbli> getByKodeAndPage(String kode, Integer page, Integer pageSize) {
		return kbliRepository.getByKodeAndPage(kode, page, pageSize);
	}

	@Override
	public List<Kbli> getByKategori(String kategori) {
		return kbliRepository.getByKategori(kategori);
	}

	@Override
	public List<Kbli> getByKategoriAndPage(String kategori, Integer page, Integer pageSize) {
		return kbliRepository.getByKategoriAndPage(kategori, page, pageSize);
	}

}
