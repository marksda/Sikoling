package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;

public class RegisterDokumenService implements IRegisterDokumenService {
	
	private final IRegisterDokumenRepository registerDokumenRepository;
	
	public RegisterDokumenService(IRegisterDokumenRepository registerDokumenRepository) {
		this.registerDokumenRepository = registerDokumenRepository;
	}

	@Override
	public RegisterDokumen save(RegisterDokumen registerDokumen) {
		return registerDokumenRepository.save(registerDokumen);
	}

	@Override
	public RegisterDokumen update(RegisterDokumen registerDokumen) {
		return registerDokumenRepository.update(registerDokumen);
	}

	@Override
	public List<RegisterDokumen> getAllByPage(Integer page, Integer pageSize) {
		return registerDokumenRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByNamaPerusahaan(String namaPerusahaan) {
		return registerDokumenRepository.getByNamaPerusahaan(namaPerusahaan);
	}

	@Override
	public List<RegisterDokumen> getByNamaPerusahaanAndPage(String namaPerusahaan, Integer page, Integer pageSize) {
		return registerDokumenRepository.getByNamaPerusahaanAndPage(namaPerusahaan, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByIdPerusahaan(String idPerusahaan) {
		return registerDokumenRepository.getByIdPerusahaan(idPerusahaan);
	}

	@Override
	public List<RegisterDokumen> getByIdPerusahaanAndPage(String idPerusahaan, Integer page, Integer pageSize) {
		return registerDokumenRepository.getByIdPerusahaanAndPage(idPerusahaan, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByNamaDokumen(String namaDokumen) {
		return registerDokumenRepository.getByNamaDokumen(namaDokumen);
	}

	@Override
	public List<RegisterDokumen> getByNamaDokumenAndPage(String namaDokumen, Integer page, Integer pageSize) {
		return registerDokumenRepository.getByNamaDokumenAndPage(namaDokumen, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getByIdDokumen(String idDokumen) {
		return registerDokumenRepository.getByIdDokumen(idDokumen);
	}

	@Override
	public List<RegisterDokumen> getByIdDokumenAndPage(String idDokumen, Integer page, Integer pageSize) {
		return registerDokumenRepository.getByIdDokumenAndPage(idDokumen, page, pageSize);
	}

	@Override
	public List<RegisterDokumen> getAll() {
		return registerDokumenRepository.getAll();
	}

}
