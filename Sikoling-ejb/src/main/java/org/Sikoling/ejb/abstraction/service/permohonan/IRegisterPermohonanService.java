package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterPermohonan;

public interface IRegisterPermohonanService {
	DeleteResponse delete(String id);
	RegisterPermohonan save(RegisterPermohonan t);
	RegisterPermohonan update(RegisterPermohonan t);
	List<RegisterPermohonan> getAll();
	List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize);
	List<RegisterPermohonan> getByIdPengakses(String idPengakses);
	List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan);
}
