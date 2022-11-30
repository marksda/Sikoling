package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.String;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class MasterDokumenRepositoryEJB implements IMasterDokumenRepository {

	@Inject
	private MasterDokumenRepositoryJPA dokumenPerusahaanRepository;
	
	@Override
	public List<String> getAll() {
		return dokumenPerusahaanRepository.getAll();
	}

	@Override
	public String save(String t) {
		return dokumenPerusahaanRepository.save(t);
	}

	@Override
	public String update(String t) {
		return dokumenPerusahaanRepository.update(t);
	}

	@Override
	public List<String> getAllByPage(Integer page, Integer pageSize) {
		return dokumenPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<String> getByNama(String nama) {
		return dokumenPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<String> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return dokumenPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	
	@Override
	public DeleteResponse delete(String Id) {
		return dokumenPerusahaanRepository.delete(Id);
	}

	
	@Override
	public String updateById(String id, String dokumen) {
		return dokumenPerusahaanRepository.updateById(id, dokumen);
	}

}
