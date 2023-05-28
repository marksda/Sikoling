package org.Sikoling.ejb.abstraction.service.kategoripelakuusaha;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IKategoriPelakuUsahaServices {
	KategoriPelakuUsaha save(KategoriPelakuUsaha kategoriPelakuUsaha);
	KategoriPelakuUsaha update(KategoriPelakuUsaha kategoriPelakuUsaha);
	KategoriPelakuUsaha updateById(String id, KategoriPelakuUsaha kategoriPelakuUsaha);
	DeleteResponse delete(String id);
	List<KategoriPelakuUsaha> getDaftarKategoriPelakuUsaha(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
