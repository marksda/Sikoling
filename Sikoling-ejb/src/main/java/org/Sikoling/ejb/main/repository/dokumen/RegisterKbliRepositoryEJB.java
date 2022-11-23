package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterKbli;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class RegisterKbliRepositoryEJB implements IRegisterKbliRepository {

	@Inject
	private RegisterKbliRepositoryJPA registerKbliRepository;

	@Override
	public List<RegisterKbli> getAll() {
		return registerKbliRepository.getAll();
	}

	@Override
	public RegisterKbli save(RegisterKbli t) {
		return registerKbliRepository.save(t);
	}

	@Override
	public RegisterKbli update(RegisterKbli t) {
		return registerKbliRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String nib, String kode) {
		return registerKbliRepository.delete(nib, kode);
	}

	@Override
	public RegisterKbli updateById(String nib, String kode, RegisterKbli registerKbli) {
		return registerKbliRepository.updateById(nib, kode, registerKbli);
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
	
	
}
