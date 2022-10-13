package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DokumenRepositoryEJB implements IDokumenRepository {

	@Inject
	private DokumenRepositoryJPA detailDokumenPerusahaanRepository;
	
	@Override
	public List<Dokumen> getAll() {
		return detailDokumenPerusahaanRepository.getAll();
	}

	@Override
	public Dokumen save(Dokumen t) {
		return detailDokumenPerusahaanRepository.save(t);
	}

	@Override
	public Dokumen update(Dokumen t) {
		return detailDokumenPerusahaanRepository.update(t);
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		return detailDokumenPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Dokumen> getByNama(String nama) {
		return detailDokumenPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return detailDokumenPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
