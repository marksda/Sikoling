package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;

public class KategoriDokumenService implements IKategoriDokumenService {

	private final IKategoriDokumenRepository kategoriDokumenRepository;	
	
	public KategoriDokumenService(IKategoriDokumenRepository kategoriDokumenRepository) {
		this.kategoriDokumenRepository = kategoriDokumenRepository;
	}

	@Override
	public KategoriDokumen save(KategoriDokumen kategoriDokumen) {
		return kategoriDokumenRepository.save(kategoriDokumen);
	}

	@Override
	public KategoriDokumen update(KategoriDokumen kategoriDokumen) {
		return kategoriDokumenRepository.update(kategoriDokumen);
	}

	@Override
	public List<KategoriDokumen> getAllByPage(Integer page, Integer pageSize) {
		return kategoriDokumenRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<KategoriDokumen> getByNama(String nama) {
		return kategoriDokumenRepository.getByNama(nama);
	}

	@Override
	public List<KategoriDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kategoriDokumenRepository.getByNamaAndPage(nama, page, pageSize);
	}
	
	@Override
	public List<KategoriDokumen> getAll() {
		return kategoriDokumenRepository.getAll();
	}

	@Override
	public DeleteResponse delete(String Id) {
		return kategoriDokumenRepository.delete(Id);
	}
	

	@Override
	public KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen) {
		return kategoriDokumenRepository.updateById(id, kategoriDokumen);
	}

}
