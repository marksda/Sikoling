package org.Sikoling.ejb.abstraction.service.pelakuusaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPelakuUsahaServices {
	PelakuUsaha save(PelakuUsaha t) throws IOException;
	PelakuUsaha update(PelakuUsaha t);
	PelakuUsaha updateId(String idLama, PelakuUsaha t) throws IOException;
	PelakuUsaha delete(PelakuUsaha t) throws IOException;
	List<PelakuUsaha> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
