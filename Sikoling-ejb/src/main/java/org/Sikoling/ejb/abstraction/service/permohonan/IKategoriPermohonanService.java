package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;

public interface IKategoriPermohonanService {	
	KategoriPermohonan save(KategoriPermohonan t) throws IOException;
	KategoriPermohonan update(KategoriPermohonan t);
	KategoriPermohonan updateId(String idLama, KategoriPermohonan t) throws IOException;
	KategoriPermohonan delete(KategoriPermohonan t) throws IOException;
	List<KategoriPermohonan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
