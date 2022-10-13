package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.KategoriDokumenPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class KategoriDokumenRepositoryEJB implements IKategoriDokumenRepository {

	@Inject
	private KategoriDokumenPerusahaanRepositoryJPA kategoriDokumenPerusahaanRepository;
	
	@Override
	public List<KategoriDokumenPerusahaan> getAll() {
		return kategoriDokumenPerusahaanRepository.getAll();
	}

	@Override
	public KategoriDokumenPerusahaan save(KategoriDokumenPerusahaan t, String t2) {
		return kategoriDokumenPerusahaanRepository.save(t, t2);
	}

	@Override
	public KategoriDokumenPerusahaan update(KategoriDokumenPerusahaan t, String t2) {
		return kategoriDokumenPerusahaanRepository.update(t, t2);
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
