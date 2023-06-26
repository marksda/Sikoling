package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;

public interface IStatusPengurusPermohonanService {
	StatuswaliPermohonan save(StatuswaliPermohonan t) throws IOException;
	StatuswaliPermohonan update(StatuswaliPermohonan t);
	StatuswaliPermohonan updateId(String idLama, StatuswaliPermohonan t) throws IOException;
	StatuswaliPermohonan delete(StatuswaliPermohonan t) throws IOException;
	List<StatuswaliPermohonan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
