package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IKategoriPelakuUsahaRepository extends IRepository<KategoriPelakuUsaha> {
	KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha);
	DeleteResponse delete(String id);
	List<KategoriPelakuUsaha> getDaftarKategoriPelakuUsaha(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}