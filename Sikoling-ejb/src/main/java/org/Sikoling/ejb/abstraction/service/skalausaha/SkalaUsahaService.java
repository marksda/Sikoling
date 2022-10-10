package org.Sikoling.ejb.abstraction.service.skalausaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;

public class SkalaUsahaService implements ISkalaUsahaService {
	
	private final ISkalaUsahaRepository skalaUsahaRepository;	

	public SkalaUsahaService(ISkalaUsahaRepository skalaUsahaRepository) {
		this.skalaUsahaRepository = skalaUsahaRepository;
	}

	@Override
	public List<SkalaUsaha> getALL() {
		return skalaUsahaRepository.getAll();
	}

	@Override
	public SkalaUsaha save(SkalaUsaha skalaUsaha) {
		return skalaUsahaRepository.save(skalaUsaha);
	}

	@Override
	public SkalaUsaha update(SkalaUsaha skalaUsaha) {
		return skalaUsahaRepository.update(skalaUsaha);
	}

	@Override
	public List<SkalaUsaha> getAllByPage(Integer page, Integer pageSize) {
		return skalaUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<SkalaUsaha> getByNama(String nama) {
		return skalaUsahaRepository.getByNama(nama);
	}

	@Override
	public List<SkalaUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return skalaUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
