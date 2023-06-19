package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;

public interface IStatusPengurusPermohonanService {
	StatusPengurusPermohonan save(StatusPengurusPermohonan t) throws IOException;
	StatusPengurusPermohonan update(StatusPengurusPermohonan t);
	StatusPengurusPermohonan updateId(String idLama, StatusPengurusPermohonan t) throws IOException;
	StatusPengurusPermohonan delete(StatusPengurusPermohonan t) throws IOException;
	List<StatusPengurusPermohonan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
