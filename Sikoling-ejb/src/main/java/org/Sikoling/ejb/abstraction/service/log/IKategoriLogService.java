package org.Sikoling.ejb.abstraction.service.log;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;

public interface IKategoriLogService {
	KategoriFlowLog save(KategoriFlowLog t) throws IOException;
	KategoriFlowLog update(KategoriFlowLog t);
	KategoriFlowLog updateId(String idLama, KategoriFlowLog t) throws IOException;
	KategoriFlowLog delete(KategoriFlowLog t) throws IOException;
	List<KategoriFlowLog> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
