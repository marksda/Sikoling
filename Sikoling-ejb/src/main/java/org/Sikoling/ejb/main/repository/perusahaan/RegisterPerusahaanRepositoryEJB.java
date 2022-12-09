package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class RegisterPerusahaanRepositoryEJB implements IRegisterPerusahaanRepository {
	
	@Inject
	private RegisterPerusahaanRepositoryJPA registerPerusahaanRepository;

	@Override
	public List<RegisterPerusahaan> getAll() {		
		return registerPerusahaanRepository.getAll();
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {
		return registerPerusahaanRepository.save(t);
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		return registerPerusahaanRepository.update(t);
	}

	@Override
	public List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return registerPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<RegisterPerusahaan> getByNama(String nama) {
		return registerPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return registerPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public RegisterPerusahaan getByNpwp(String npwp) {
		return registerPerusahaanRepository.getByNpwp(npwp);
	}
	
	@Override
	public Boolean cekById(String id) {
		return registerPerusahaanRepository.cekById(id);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		return registerPerusahaanRepository.delete(id);
	}

	
	@Override
	public List<RegisterPerusahaan> getByIdPerson(String personId) {
		return registerPerusahaanRepository.getByIdPerson(personId);
	}

}
