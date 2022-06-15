package org.Sikoling.ejb.abstraction.service.jabatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;

public class JabatanService implements IJabatanService {
	
	private final IJabatanRepository jabatanRepository;

	public JabatanService(IJabatanRepository jabatanRepository) {
		super();
		this.jabatanRepository = jabatanRepository;
	}

	@Override
 	public Jabatan save(Jabatan jabatan) {
		return jabatanRepository.save(jabatan);
	}

	@Override
	public Jabatan update(Jabatan jabatan) {
		return jabatanRepository.update(jabatan);
	}

	@Override
	public List<Jabatan> getAll() {
		return jabatanRepository.getAll();
	}

	@Override
	public List<Jabatan> getAllByPage(Integer page, Integer pageSize) {
		return jabatanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Jabatan> getByNama(String nama) {
		return jabatanRepository.getByNama(nama);
	}

	@Override
	public List<Jabatan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return jabatanRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
