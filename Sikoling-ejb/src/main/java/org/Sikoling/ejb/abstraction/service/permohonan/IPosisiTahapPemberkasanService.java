package org.Sikoling.ejb.abstraction.service.permohonan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public interface IPosisiTahapPemberkasanService {
	PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) throws IOException;
	PosisiTahapPemberkasan update(PosisiTahapPemberkasan t);
	PosisiTahapPemberkasan updateId(String idLama, PosisiTahapPemberkasan t) throws IOException;
	PosisiTahapPemberkasan delete(PosisiTahapPemberkasan t) throws IOException;
	List<PosisiTahapPemberkasan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
