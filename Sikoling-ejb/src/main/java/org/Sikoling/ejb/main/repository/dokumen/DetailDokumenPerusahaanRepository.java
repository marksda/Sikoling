package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DetailDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IDetailDokumenPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DetailDokumenPerusahaanRepository implements IDetailDokumenPerusahaanRepository {

	@Inject
	private DetailDokumenPerusahaanRepositoryJPA detailDokumenPerusahaanRepository;
	
	@Override
	public List<DetailDokumenPerusahaan> getAll() {
		return detailDokumenPerusahaanRepository.getAll();
	}

	@Override
	public DetailDokumenPerusahaan save(DetailDokumenPerusahaan t) {
		return detailDokumenPerusahaanRepository.save(t);
	}

	@Override
	public DetailDokumenPerusahaan update(DetailDokumenPerusahaan t) {
		return detailDokumenPerusahaanRepository.update(t);
	}

	@Override
	public List<DetailDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return detailDokumenPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<DetailDokumenPerusahaan> getByNama(String nama) {
		return detailDokumenPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<DetailDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return detailDokumenPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
