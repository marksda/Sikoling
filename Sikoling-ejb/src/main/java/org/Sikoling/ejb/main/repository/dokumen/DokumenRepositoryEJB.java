package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DokumenRepositoryEJB implements IMasterDokumenRepository {

	@Inject
	private DokumenRepositoryJPA dokumenPerusahaanRepository;
	
	@Override
	public List<Dokumen> getAll() {
		return dokumenPerusahaanRepository.getAll();
	}

	@Override
	public Dokumen save(Dokumen t) {
		return dokumenPerusahaanRepository.save(t);
	}

	@Override
	public Dokumen update(Dokumen t) {
		return dokumenPerusahaanRepository.update(t);
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		return dokumenPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Dokumen> getByNama(String nama) {
		return dokumenPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return dokumenPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public DeleteResponse delete(String Id) {
		return dokumenPerusahaanRepository.delete(Id);
	}

	
	@Override
	public Dokumen updateById(String id, Dokumen dokumen) {
		return dokumenPerusahaanRepository.updateById(id, dokumen);
	}

}
