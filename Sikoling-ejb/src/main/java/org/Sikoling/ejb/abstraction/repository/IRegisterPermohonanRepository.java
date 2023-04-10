package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;

public interface IRegisterPermohonanRepository extends IRepository<RegisterPermohonan> {
	DeleteResponse delete(String id);
	List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize);
	List<RegisterPermohonan> getByIdPengakses(String idPengakses);
	List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan);
	List<RegisterPermohonan> getByIdPenerima(String idPenerima);
	List<RegisterPermohonan> getByIdPengirim(String idPengirim);
	List<RegisterPermohonan> getByIdPengirimAtauPenerima(String idPengirim, String idPenerima);
	List<RegisterPermohonan> getByIdPengirimAtauPenerimaOnProcess(String idPengirim, String idPenerima);
	List<RegisterPermohonan> getDaftarPermohonan(QueryParamFilters queryParamFilters);
}