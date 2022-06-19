package org.Sikoling.ejb.abstraction.service.kabupaten;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;

public class KabupatenService implements IKabupatenService {
	
	private final IKabupatenRepository kabupatenRepository;

	public KabupatenService(IKabupatenRepository kabupatenRepository) {
		this.kabupatenRepository = kabupatenRepository;
	}
	
	@Override
	public Kabupaten save(Kabupaten kabupaten, String idPropinsi) {		
		return kabupatenRepository.save(kabupaten, idPropinsi);
	}

	@Override
	public Kabupaten update(Kabupaten kabupaten, String idPropinsi) {
		return kabupatenRepository.update(kabupaten, idPropinsi);
	}

	@Override
	public List<Kabupaten> getAll() {
		return kabupatenRepository.getAll();
	}

	@Override
	public List<Kabupaten> getAllByPage(Integer page, Integer pageSize) {
		return kabupatenRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kabupaten> getByQueryNama(String nama) {
		return kabupatenRepository.getByNama(nama);
	}

	@Override
	public List<Kabupaten> getByQueryNamAndPage(String nama, Integer page, Integer pageSize) {
		return kabupatenRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kabupaten> getByIdPropinsi(String idPropinsi) {
		return kabupatenRepository.getByPropinsi(idPropinsi);
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize) {
		return kabupatenRepository.getByPropinsiAndPage(idPropinsi, page, pageSize);
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndQueryNama(String idPropinsi, String nama) {
		return kabupatenRepository.getByPropinsiAndNama(idPropinsi, nama);
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndQueryNamaAndPage(String idPropinsi, String nama, Integer page, Integer pageSize) {
		return kabupatenRepository.getByPropinsiAndNamaAndPage(idPropinsi, nama, page, pageSize);
	}

}
