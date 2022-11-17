package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IDokumenRepository;

public class DokumenService implements IDokumenService {
	
	private final IDokumenRepository dokumenRepository;
	
	public DokumenService(IDokumenRepository dokumenRepository) {
		this.dokumenRepository = dokumenRepository;
	}

	@Override
	public Dokumen save(Dokumen dokumen) {
		return dokumenRepository.save(dokumen);
	}

	@Override
	public Dokumen update(Dokumen dokumen) {
		return dokumenRepository.update(dokumen);
	}

	@Override
	public List<Dokumen> getAll() {
		return dokumenRepository.getAll();
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		return dokumenRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Dokumen> getByNama(String nama) {
		return dokumenRepository.getByNama(nama);
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return dokumenRepository.getByNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public DeleteResponse delete(String Id) {
		return dokumenRepository.delete(Id);
	}

	
	@Override
	public Dokumen updateById(String id, Dokumen dokumen) {
		return dokumenRepository.updateById(id, dokumen);
	}

}
