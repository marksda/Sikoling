package org.Sikoling.ejb.abstraction.service.kategoripenanggungjawab;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab;
import org.Sikoling.ejb.abstraction.repository.IKategoriPenanggungJawabRepository;

public class KategoriPenanggungJawabService implements IKategoriPenanggungJawabService {
	
	private final IKategoriPenanggungJawabRepository kategoriPenanggungJawabRepository;

	public KategoriPenanggungJawabService(IKategoriPenanggungJawabRepository kategoriPenanggungJawabRepository) {
		super();
		this.kategoriPenanggungJawabRepository = kategoriPenanggungJawabRepository;
	}

	@Override
	public KategoriPenanggungJawab save(KategoriPenanggungJawab t) {
		return kategoriPenanggungJawabRepository.save(t);
	}

	@Override
	public KategoriPenanggungJawab update(KategoriPenanggungJawab t) {
		return kategoriPenanggungJawabRepository.update(t);
	}

	@Override
	public List<KategoriPenanggungJawab> getAllByPage(Integer page,	Integer pageSize) {
		return kategoriPenanggungJawabRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriPenanggungJawab> getByQueryNama(String nama) {
		return kategoriPenanggungJawabRepository.getByQueryNama(nama);
	}

	@Override
	public List<KategoriPenanggungJawab> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kategoriPenanggungJawabRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public List<KategoriPenanggungJawab> getAll() {
		return kategoriPenanggungJawabRepository.getAll();
	}

}
