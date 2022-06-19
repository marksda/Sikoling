package org.Sikoling.ejb.abstraction.service.penanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;

public class PenanggungJawabService implements IPenanggungJawabService {
	
	private final IPenanggungJawabRepository penanggungJawabRepository;

	public PenanggungJawabService(IPenanggungJawabRepository penanggungJawabRepository) {
		super();
		this.penanggungJawabRepository = penanggungJawabRepository;
	}

	@Override
	public PenanggungJawab save(PenanggungJawab t, String idPemrakarsa) {
		return penanggungJawabRepository.save(t, idPemrakarsa);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t, String idPemrakarsa) {
		return penanggungJawabRepository.update(t, idPemrakarsa);
	}

	@Override
	public List<PenanggungJawab> getAll() {
		return penanggungJawabRepository.getAll();
	}

	@Override
	public List<PenanggungJawab> getAllByPage(Integer page, Integer pageSize) {
		return penanggungJawabRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<PenanggungJawab> getByNama(String nama) {
		return penanggungJawabRepository.getByNama(nama);
	}

	@Override
	public List<PenanggungJawab> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return penanggungJawabRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
