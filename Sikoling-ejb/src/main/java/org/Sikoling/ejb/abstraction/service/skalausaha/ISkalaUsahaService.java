package org.Sikoling.ejb.abstraction.service.skalausaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;

public interface ISkalaUsahaService {
	SkalaUsaha save(SkalaUsaha t) throws IOException;
	SkalaUsaha update(SkalaUsaha t);
	SkalaUsaha updateId(String idLama, SkalaUsaha t) throws IOException;
	SkalaUsaha delete(SkalaUsaha t) throws IOException;
	List<SkalaUsaha> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}