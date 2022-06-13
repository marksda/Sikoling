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
	public PenanggungJawab save(PenanggungJawab t) {
		return penanggungJawabRepository.save(t);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t) {
		return penanggungJawabRepository.update(t);
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
	public List<PenanggungJawab> getByQueryNama(String nama) {
		return penanggungJawabRepository.getByQueryNama(nama);
	}

	@Override
	public List<PenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return penanggungJawabRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilik(String idPemilik) {
		return penanggungJawabRepository.getAllByPemilik(idPemilik);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilikAndPage(String idPemilik, Integer page, Integer pageSize) {
		return penanggungJawabRepository.getAllByPemilikAndPage(idPemilik, page, pageSize);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilikAndNama(String idPemilik, String nama) {
		return penanggungJawabRepository.getAllByPemilikAndNama(idPemilik, nama);
	}

	@Override
	public List<PenanggungJawab> getAllByPemilikAndNamaAndPage(String idPemilik, String nama, Integer page,
			Integer pageSize) {
		return penanggungJawabRepository.getAllByPemilikAndNamaAndPage(idPemilik, nama, page, pageSize);
	}

}
