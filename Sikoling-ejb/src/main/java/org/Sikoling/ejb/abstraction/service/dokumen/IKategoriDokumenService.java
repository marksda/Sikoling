package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;

public interface IKategoriDokumenService {
	KategoriDokumen save(KategoriDokumen kategoriDokumen);
	KategoriDokumen update(KategoriDokumen kategoriDokumen);
	KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen);
	DeleteResponse delete(String Id);
	List<KategoriDokumen> getDaftarKategoriDokumen(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);	
}
