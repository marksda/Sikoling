package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;

public class RegisterKbliService implements IRegisterKbliService {
	
	private final IRegisterKbliRepository registerKbliRepository;

	public RegisterKbliService(IRegisterKbliRepository registerKbliRepository) {
		this.registerKbliRepository = registerKbliRepository;
	}

	@Override
	public RegisterKbli save(RegisterKbli registerKbli) {
		return registerKbliRepository.save(registerKbli);
	}

	@Override
	public RegisterKbli update(RegisterKbli registerKbli) {
		return registerKbliRepository.update(registerKbli);
	}

	@Override
	public DeleteResponse delete(String nib, String kode) {
		return registerKbliRepository.delete(nib, kode);
	}

	@Override
	public List<RegisterKbli> getAllByPage(Integer page, Integer pageSize) {
		return registerKbliRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<RegisterKbli> getByNama(String nama) {
		return registerKbliRepository.getByNama(nama);
	}

	@Override
	public List<RegisterKbli> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return registerKbliRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<RegisterKbli> getByKode(String kode) {
		return registerKbliRepository.getByKode(kode);
	}

	@Override
	public List<RegisterKbli> getByKodeAndPage(String kode, Integer page, Integer pageSize) {
		return registerKbliRepository.getByKodeAndPage(kode, page, pageSize);
	}

	@Override
	public List<RegisterKbli> getByNib(String nib) {
		return registerKbliRepository.getByNib(nib);
	}

	@Override
	public List<RegisterKbli> getByNibAndPage(String nib, Integer page, Integer pageSize) {
		return registerKbliRepository.getByNibAndPage(nib, page, pageSize);
	}

	
	@Override
	public List<RegisterKbli> getAll() {
		return registerKbliRepository.getAll();
	}

}
