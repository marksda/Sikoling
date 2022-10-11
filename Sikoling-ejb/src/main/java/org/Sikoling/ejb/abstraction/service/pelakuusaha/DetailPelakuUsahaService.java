package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailPelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IDetailPelakuUsahaRepository;

public class DetailPelakuUsahaService implements IDetailPelakuUsahaServices {
	
	private final IDetailPelakuUsahaRepository detailPelakuUsahaRepository;

	public DetailPelakuUsahaService(IDetailPelakuUsahaRepository pelakuUsahaRepository) {
		super();
		this.detailPelakuUsahaRepository = pelakuUsahaRepository;
	}

	@Override
	public List<DetailPelakuUsaha> getALL() {
		return detailPelakuUsahaRepository.getAll();
	}

	@Override
	public DetailPelakuUsaha save(DetailPelakuUsaha detailPelakuUsaha) {
		return detailPelakuUsahaRepository.save(detailPelakuUsaha);
	}

	@Override
	public DetailPelakuUsaha update(DetailPelakuUsaha detailPelakuUsaha) {
		return detailPelakuUsahaRepository.save(detailPelakuUsaha);
	}

	@Override
	public List<DetailPelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return detailPelakuUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<DetailPelakuUsaha> getByNama(String nama) {
		return detailPelakuUsahaRepository.getByNama(nama);
	}

	@Override
	public List<DetailPelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return detailPelakuUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
