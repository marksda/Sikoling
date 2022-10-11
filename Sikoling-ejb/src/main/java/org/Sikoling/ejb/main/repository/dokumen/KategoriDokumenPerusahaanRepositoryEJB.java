package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriDokumenPerusahaanRepositoryEJB implements IKategoriDokumenPerusahaanRepository {

	@Inject
	private KategoriDokumenPerusahaanRepositoryJPA kategoriDokumenPerusahaanRepository;
	
	@Override
	public List<KategoriDokumenPerusahaan> getAll() {
		return kategoriDokumenPerusahaanRepository.getAll();
	}

	@Override
	public KategoriDokumenPerusahaan save(KategoriDokumenPerusahaan t) {
		return kategoriDokumenPerusahaanRepository.save(t);
	}

	@Override
	public KategoriDokumenPerusahaan update(KategoriDokumenPerusahaan t) {
		return kategoriDokumenPerusahaanRepository.update(t);
	}

	@Override
	public List<KategoriDokumenPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return kategoriDokumenPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriDokumenPerusahaan> getByNama(String nama) {
		return kategoriDokumenPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<KategoriDokumenPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kategoriDokumenPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

}
