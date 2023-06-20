package org.Sikoling.ejb.abstraction.service.produk;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IProdukService {	
	Produk save(Produk t) throws IOException;
	Produk update(Produk t);
	Produk updateId(String idLama, Produk t) throws IOException;
	Produk delete(Produk t) throws IOException;
	List<Produk> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
