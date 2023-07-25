package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public interface IDokumenService {
	Dokumen save(Dokumen t) throws IOException;
	Dokumen update(Dokumen t);
	Dokumen updateId(String idLama, Dokumen t) throws IOException;
	Dokumen delete(Dokumen t) throws IOException;
	List<Dokumen> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
