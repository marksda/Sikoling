package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli2020;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;

public class KbliService implements IKbliService {
	
	private final IKbliRepository kbliRepository;	

	public KbliService(IKbliRepository kbliRepository) {
		this.kbliRepository = kbliRepository;
	}

	@Override
	public Kbli2020 save(Kbli2020 kbli) {
		return kbliRepository.save(kbli);
	}

	@Override
	public Kbli2020 update(Kbli2020 kbli) {
		return kbliRepository.update(kbli);
	}

	@Override
	public Kbli2020 updateById(String id, Kbli2020 kbli) {
		return kbliRepository.updateById(id, kbli);
	}

	@Override
	public DeleteResponse delete(String kode) {
		return kbliRepository.delete(kode);
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

	@Override
	public List<Kbli2020> getAll() {
		return kbliRepository.getAll();
	}

}
