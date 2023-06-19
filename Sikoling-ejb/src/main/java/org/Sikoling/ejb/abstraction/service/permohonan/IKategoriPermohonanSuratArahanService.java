package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;

public interface IKategoriPermohonanSuratArahanService {
	KategoriPermohonanSuratArahan save(KategoriPermohonanSuratArahan t) throws IOException;
	KategoriPermohonanSuratArahan update(KategoriPermohonanSuratArahan t);
	KategoriPermohonanSuratArahan updateId(String idLama, KategoriPermohonanSuratArahan t) throws IOException;
	KategoriPermohonanSuratArahan delete(KategoriPermohonanSuratArahan t) throws IOException;
	List<KategoriPermohonanSuratArahan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
