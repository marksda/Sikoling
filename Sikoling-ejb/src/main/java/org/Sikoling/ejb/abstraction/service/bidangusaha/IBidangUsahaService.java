package org.Sikoling.ejb.abstraction.service.bidangusaha;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IBidangUsahaService {
	BidangUsaha save(BidangUsaha t) throws IOException;
	BidangUsaha update(BidangUsaha t);
	BidangUsaha updateId(String idLama, BidangUsaha t) throws IOException;
	BidangUsaha delete(BidangUsaha t) throws IOException;
	List<BidangUsaha> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);	
}
