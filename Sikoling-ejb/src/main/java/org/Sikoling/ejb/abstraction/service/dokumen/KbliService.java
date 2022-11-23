package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Kbli;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;

public class KbliService implements IKbliService {
	
	private final IKbliRepository kbliRepository;	

	public KbliService(IKbliRepository kbliRepository) {
		this.kbliRepository = kbliRepository;
	}

	@Override
	public Kbli save(Kbli kbli) {
		return kbliRepository.save(kbli);
	}

	@Override
	public Kbli update(Kbli kbli) {
		return kbliRepository.update(kbli);
	}

	@Override
	public Kbli updateById(String id, Kbli kbli) {
		return kbliRepository.updateById(id, kbli);
	}

	@Override
	public DeleteResponse delete(String kode) {
		return kbliRepository.delete(kode);
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

	@Override
	public List<Kbli> getAll() {
		return kbliRepository.getAll();
	}

}
