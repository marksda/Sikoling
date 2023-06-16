package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;

public interface IKategoriDokumenService {
	KategoriDokumen save(KategoriDokumen t) throws IOException;
	KategoriDokumen update(KategoriDokumen t);
	KategoriDokumen updateId(String idLama, KategoriDokumen t) throws IOException;
	KategoriDokumen delete(KategoriDokumen t) throws IOException;
	List<KategoriDokumen> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);	
}
