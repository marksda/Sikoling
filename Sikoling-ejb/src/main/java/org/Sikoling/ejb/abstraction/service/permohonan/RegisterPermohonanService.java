package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;

public class RegisterPermohonanService implements IRegisterPermohonanService {
	
	private final IRegisterPermohonanRepository registerPermohonanRepository;

	public RegisterPermohonanService(IRegisterPermohonanRepository registerPermohonanRepository) {
		this.registerPermohonanRepository = registerPermohonanRepository;
	}

	@Override
	public RegisterPermohonan save(RegisterPermohonan t) {
		return registerPermohonanRepository.save(t);
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		return registerPermohonanRepository.update(t);
	}

	@Override
	public List<RegisterPermohonan> getAll() {
		return registerPermohonanRepository.getAll();
	}

	@Override
	public List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize) {
		return registerPermohonanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<RegisterPermohonan> getByIdPengakses(String idPengakses) {
		return registerPermohonanRepository.getByIdPengakses(idPengakses);
	}

	@Override
	public List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan) {
		return registerPermohonanRepository.getByIdPerusahaan(idPerusahaan);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		return registerPermohonanRepository.delete(id);
	}
	
	@Override
	public List<RegisterPermohonan> getByIdPenerima(String idPenerima) {
		return registerPermohonanRepository.getByIdPenerima(idPenerima);
	}
	
	@Override
	public List<RegisterPermohonan> getByIdPengirim(String idPengirim) {
		return registerPermohonanRepository.getByIdPengirim(idPengirim);
	}

	@Override
	public List<RegisterPermohonan> getByIdPengirimAtauIdPenerima(String idPengirim, String idPenerima) {
		return registerPermohonanRepository.getByIdPengirimAtauPenerima(idPengirim, idPenerima);
	}
	
	@Override
	public List<RegisterPermohonan> getByIdPengirimAtauPenerimaOnProcess(String idPengirim, String idPenerima) {
		return registerPermohonanRepository.getByIdPengirimAtauPenerimaOnProcess(idPengirim, idPenerima);
	}

	
	@Override
	public List<RegisterPermohonan> getDaftarPermohonan(QueryParamFilters queryParamFilters) {
		return registerPermohonanRepository.getDaftarPermohonan(queryParamFilters);
	}

}
