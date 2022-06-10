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
	public org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab save(KategoriPenanggungJawab t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab update(
			org.Sikoling.ejb.abstraction.entity.KategoriPenanggungJawab t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KategoriPenanggungJawab> getAllByPage(Integer page,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KategoriPenanggungJawab> getByQueryNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KategoriPenanggungJawab> getByQueryNamaAndPage(String nama,
			Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
