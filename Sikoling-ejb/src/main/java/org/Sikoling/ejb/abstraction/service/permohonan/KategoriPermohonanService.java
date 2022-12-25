package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;

public class KategoriPermohonanService implements IKategoriPermohonanService {
	
	private final IKategoriPermohonanRepository kategoriPermohonanRepository;

	public KategoriPermohonanService(IKategoriPermohonanRepository kategoriPermohonanRepository) {
		this.kategoriPermohonanRepository = kategoriPermohonanRepository;
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriPermohonanRepository.delete(id);
	}

	@Override
	public KategoriPermohonan save(KategoriPermohonan t) {
		return kategoriPermohonanRepository.save(t);
	}

	@Override
	public KategoriPermohonan update(KategoriPermohonan t) {
		return kategoriPermohonanRepository.update(t);
	}

	@Override
	public List<KategoriPermohonan> getAll() {
		return kategoriPermohonanRepository.getAll();
	}

	@Override
	public List<KategoriPermohonan> getByNama(String nama) {
		return kategoriPermohonanRepository.getByNama(nama);
	}

}
