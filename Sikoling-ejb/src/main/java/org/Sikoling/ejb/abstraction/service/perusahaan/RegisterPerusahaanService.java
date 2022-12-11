package org.Sikoling.ejb.abstraction.service.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;

public class RegisterPerusahaanService implements IRegisterPerusahaanService {
	
	private final IRegisterPerusahaanRepository perusahaanRepository;

	public RegisterPerusahaanService(IRegisterPerusahaanRepository pemrakarsaRepository) {
		this.perusahaanRepository = pemrakarsaRepository;
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {
		return perusahaanRepository.save(t);
	}

	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		return perusahaanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return perusahaanRepository.delete(id);
	}

	@Override
	public List<RegisterPerusahaan> getAll() {
		return perusahaanRepository.getAll();
	}

	@Override
	public List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return perusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<RegisterPerusahaan> getByNama(String nama) {
		return perusahaanRepository.getByNama(nama);
	}

	@Override
	public List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return perusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public Boolean cekById(String id) {
		return perusahaanRepository.cekById(id);
	}
	
	@Override
	public RegisterPerusahaan getByNpwp(String npwp) {
		return perusahaanRepository.getByNpwp(npwp);
	}
	
	@Override
	public List<RegisterPerusahaan> getByIdKreator(String idKreator) {
		return perusahaanRepository.getByIdKreator(idKreator);
	}
	
	@Override
	public List<RegisterPerusahaan> getByIdLinkKepemilikan(String idLinkKepemilikan) {
		return perusahaanRepository.getByIdLinkKepemilikan(idLinkKepemilikan);
	}


	@Override
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idPerson, String idPerusahaan) {
		return perusahaanRepository.deleteLinkKepemilikanPerusahaan(idPerson, idPerusahaan);
	}

	
}
