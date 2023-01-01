package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class Kbli2020RepositoryEJB implements IKbliRepository {
	
	@Inject
	private Kbli2020RepositoryJPA kbliRepository;
	
	@Override
	public List<Kbli2020> getAll() {
		return kbliRepository.getAll();
	}

	@Override
	public Kbli2020 save(Kbli2020 t) {
		return kbliRepository.save(t);
	}

	@Override
	public Kbli2020 update(Kbli2020 t) {
		return kbliRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String kode) {
		return kbliRepository.delete(kode);
	}

	@Override
	public Kbli2020 updateById(String id, Kbli2020 kbli) {
		return kbliRepository.updateById(id, kbli);
	}

	@Override
	public List<Kbli2020> getAllByPage(Integer page, Integer pageSize) {
		return kbliRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kbli2020> getByNama(String nama) {
		return kbliRepository.getByNama(nama);
	}

	@Override
	public List<Kbli2020> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kbliRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kbli2020> getByKode(String kode) {
		return kbliRepository.getByKode(kode);
	}

	@Override
	public List<Kbli2020> getByKodeAndPage(String kode, Integer page, Integer pageSize) {
		return kbliRepository.getByKodeAndPage(kode, page, pageSize);
	}

	@Override
	public List<Kbli2020> getByKategori(String kategori) {
		return kbliRepository.getByKategori(kategori);
	}

	@Override
	public List<Kbli2020> getByKategoriAndPage(String kategori, Integer page, Integer pageSize) {
		return kbliRepository.getByKategoriAndPage(kategori, page, pageSize);
	}

}
