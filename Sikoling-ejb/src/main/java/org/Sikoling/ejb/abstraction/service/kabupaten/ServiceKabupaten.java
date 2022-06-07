package org.Sikoling.ejb.abstraction.service.kabupaten;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;

import jakarta.inject.Inject;

public class ServiceKabupaten implements IServiceKabupaten {
	
	@Inject
	private final IKabupatenRepository kabupatenRepository;

	public ServiceKabupaten(IKabupatenRepository kabupatenRepository) {
		this.kabupatenRepository = kabupatenRepository;
	}
	

	@Override
	public Kabupaten save(Kabupaten propinsi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kabupaten update(Kabupaten propinsi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getByQueryNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getByQueryNamAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getByIdPropinsi(String idPropinsi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndPage(String idPropinsi, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndQueryNama(String idPropinsi, String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kabupaten> getByIdPropinsiAndQueryNamaAndPage(String idPropinsi, String nama) {
		// TODO Auto-generated method stub
		return null;
	}

}
