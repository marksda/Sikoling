package org.Sikoling.ejb.abstraction.service.propinsi;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;

public class PropinsiService implements IPropinsiService {
	
	private final IPropinsiRepository propinsiRepository;

	public PropinsiService(IPropinsiRepository propinsiRepository) {
		this.propinsiRepository = propinsiRepository;
	}

	@Override
	public List<Propinsi> getAll() {
		return propinsiRepository.getAll();
	}

	@Override
	public Propinsi save(Propinsi propinsi) {
		return propinsiRepository.save(propinsi);
	}

	@Override
	public Propinsi update(Propinsi propinsi) {
		return propinsiRepository.update(propinsi);
	}

	@Override
	public List<Propinsi> getAllByPage(Integer page, Integer pageSize) {
		return propinsiRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Propinsi> getByNama(String nama) {
		return propinsiRepository.getByNama(nama);
	}

	@Override
	public List<Propinsi> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return propinsiRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
