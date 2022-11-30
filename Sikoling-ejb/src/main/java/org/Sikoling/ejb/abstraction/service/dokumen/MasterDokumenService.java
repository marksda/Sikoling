package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.String;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;

public class MasterDokumenService implements IMasterDokumenService {
	
	private final IMasterDokumenRepository dokumenRepository;
	
	public MasterDokumenService(IMasterDokumenRepository dokumenRepository) {
		this.dokumenRepository = dokumenRepository;
	}

	@Override
	public String save(String dokumen) {
		return dokumenRepository.save(dokumen);
	}

	@Override
	public String update(String dokumen) {
		return dokumenRepository.update(dokumen);
	}

	@Override
	public List<String> getAll() {
		return dokumenRepository.getAll();
	}

	@Override
	public List<String> getAllByPage(Integer page, Integer pageSize) {
		return dokumenRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<String> getByNama(String nama) {
		return dokumenRepository.getByNama(nama);
	}

	@Override
	public List<String> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return dokumenRepository.getByNamaAndPage(nama, page, pageSize);
	}
	
	@Override
	public DeleteResponse delete(String Id) {
		return dokumenRepository.delete(Id);
	}
	
	@Override
	public String updateById(String id, String dokumen) {
		return dokumenRepository.updateById(id, dokumen);
	}

}
