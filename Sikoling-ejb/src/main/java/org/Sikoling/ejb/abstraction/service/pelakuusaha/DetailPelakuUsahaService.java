package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.repository.IDetailPelakuUsahaRepository;

public class DetailPelakuUsahaService implements IDetailPelakuUsahaServices {
	
	private final IDetailPelakuUsahaRepository detailPelakuUsahaRepository;

	public DetailPelakuUsahaService(IDetailPelakuUsahaRepository pelakuUsahaRepository) {
		super();
		this.detailPelakuUsahaRepository = pelakuUsahaRepository;
	}

	@Override
	public List<PelakuUsaha> getALL() {
		return detailPelakuUsahaRepository.getAll();
	}

	@Override
	public PelakuUsaha save(PelakuUsaha detailPelakuUsaha) {
		return detailPelakuUsahaRepository.save(detailPelakuUsaha);
	}

	@Override
	public PelakuUsaha update(PelakuUsaha detailPelakuUsaha) {
		return detailPelakuUsahaRepository.save(detailPelakuUsaha);
	}

	@Override
	public List<PelakuUsaha> getAllByPage(Integer page, Integer pageSize) {
		return detailPelakuUsahaRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<PelakuUsaha> getByNama(String nama) {
		return detailPelakuUsahaRepository.getByNama(nama);
	}

	@Override
	public List<PelakuUsaha> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return detailPelakuUsahaRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
