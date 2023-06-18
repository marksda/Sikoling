package org.Sikoling.ejb.abstraction.service.kategoriproduk;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IKategoriProdukService {
	KategoriProduk save(KategoriProduk t) throws IOException;
	KategoriProduk update(KategoriProduk t);
	KategoriProduk updateId(String idLama, KategoriProduk t) throws IOException;
	KategoriProduk delete(KategoriProduk t) throws IOException;
	List<KategoriProduk> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
