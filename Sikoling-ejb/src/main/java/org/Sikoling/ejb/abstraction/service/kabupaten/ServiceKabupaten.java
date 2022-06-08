package org.Sikoling.ejb.abstraction.service.kabupaten;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;

public class ServiceKabupaten implements IServiceKabupaten {
	
	private final IKabupatenRepository kabupatenRepository;

	public ServiceKabupaten(IKabupatenRepository kabupatenRepository) {
		this.kabupatenRepository = kabupatenRepository;
	}
	
	@Override
	public Kabupaten save(Kabupaten kabupaten) {		
		return kabupatenRepository.save(kabupaten);
	}

	@Override
	public Kabupaten update(Kabupaten kabupaten) {
		return kabupatenRepository.update(kabupaten);
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
		return kabupatenRepository.getByQueryNama(nama);
	}

	@Override
	public List<Kabupaten> getByQueryNamAndPage(String nama, Integer page, Integer pageSize) {
		return kabupatenRepository.getByQueryNamaAndPage(nama, page, pageSize);
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
		return kabupatenRepository.getByPropinsiAndQueryNama(idPropinsi, nama);
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndQueryNamaAndPage(String idPropinsi, String nama, Integer page, Integer pageSize) {
		return kabupatenRepository.getByPropinsiAndQueryNamaAndPage(idPropinsi, nama, page, pageSize);
	}

}
