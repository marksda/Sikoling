package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;

public interface IKategoriDokumenRepository extends IRepository<KategoriDokumen> {
	DeleteResponse delete(String Id);
	KategoriDokumen updateById(String id, KategoriDokumen kategoriDokumen);
	List<KategoriDokumen> getDaftarKategoriDokumen(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
