package org.Sikoling.ejb.abstraction.service.sex;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;

public class JenisKelaminService implements IJenisKelaminService {
	
	private IJenisKelaminRepository jenisKelaminRepository;

	public JenisKelaminService(IJenisKelaminRepository jenisKelaminRepository) {
		super();
		this.jenisKelaminRepository = jenisKelaminRepository;
	}

	@Override
 	public JenisKelamin save(JenisKelamin jenisKelamin) {		
		return jenisKelaminRepository.save(jenisKelamin);
	}

	@Override
	public JenisKelamin update(JenisKelamin jenisKelamin) {
		return jenisKelaminRepository.update(jenisKelamin);
	}
	
	@Override
	public List<JenisKelamin> getAll() {
		return jenisKelaminRepository.getAll();
	}

	@Override
	public List<JenisKelamin> getAllByPage(Integer page, Integer pageSize) {
		return jenisKelaminRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<JenisKelamin> getByQueryNama(String nama) {
		return jenisKelaminRepository.getByQueryNama(nama);
	}

	@Override
	public List<JenisKelamin> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return jenisKelaminRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
