package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;

public interface IRegisterPermohonanService {
	RegisterPermohonan save(RegisterPermohonan t) throws IOException;
	RegisterPermohonan update(RegisterPermohonan t);
	RegisterPermohonan updateId(String idLama, RegisterPermohonan t) throws IOException;
	RegisterPermohonan delete(RegisterPermohonan t) throws IOException;
	List<RegisterPermohonan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
