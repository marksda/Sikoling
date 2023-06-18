package org.Sikoling.ejb.abstraction.service.kategoripelakuusaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IKategoriPelakuUsahaServices {
	KategoriPelakuUsaha save(KategoriPelakuUsaha t) throws IOException;
	KategoriPelakuUsaha update(KategoriPelakuUsaha t);
	KategoriPelakuUsaha updateId(String idLama, KategoriPelakuUsaha t) throws IOException;
	KategoriPelakuUsaha delete(KategoriPelakuUsaha t) throws IOException;
	List<KategoriPelakuUsaha> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
