package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class RegisterDokumenRepositoryEJB implements IRegisterDokumenRepository {
	
	@Inject
	private RegisterDokumenRepositoryJPA RegisterDokumenRepository;
	
	@Override
	public List<RegisterDokumen> getAll() {
		return RegisterDokumenRepository.getAll();
	}

	@Override
	public RegisterDokumen save(RegisterDokumen t) {
		return RegisterDokumenRepository.save(t);
	}

	@Override
	public RegisterDokumen update(RegisterDokumen t) {
		return RegisterDokumenRepository.update(t);
	}

	@Override
	public List<RegisterDokumen> getAllByPage(Integer page, Integer pageSize) {
		return RegisterDokumenRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByNamaPerusahaan(String namaPerusahaan) {
		return RegisterDokumenRepository.getByNamaPerusahaan(namaPerusahaan);
	}

	@Override
	public List<RegisterDokumen> getByNamaPerusahaanAndPage(String namaPerusahaan, Integer page, Integer pageSize) {
		return RegisterDokumenRepository.getByNamaPerusahaanAndPage(namaPerusahaan, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByIdPerusahaan(String idPerusahaan) {
		return RegisterDokumenRepository.getByIdPerusahaan(idPerusahaan);
	}

	@Override
	public List<RegisterDokumen> getByIdPerusahaanAndPage(String idPerusahaan, Integer page, Integer pageSize) {
		return RegisterDokumenRepository.getByIdPerusahaanAndPage(idPerusahaan, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByNamaDokumen(String namaDokumen) {
		return RegisterDokumenRepository.getByNamaDokumen(namaDokumen);
	}

	@Override
	public List<RegisterDokumen> getByNamaDokumenAndPage(String namaDokumen, Integer page, Integer pageSize) {
		return RegisterDokumenRepository.getByNamaDokumenAndPage(namaDokumen, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByIdDokumen(String idDokumen) {
		return RegisterDokumenRepository.getByIdDokumen(idDokumen);
	}

	@Override
	public List<RegisterDokumen> getByIdDokumenAndPage(String idDokumen, Integer page, Integer pageSize) {
		return RegisterDokumenRepository.getByIdDokumenAndPage(idDokumen, page, pageSize);
	}

}
