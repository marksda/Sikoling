package org.Sikoling.ejb.abstraction.service.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public interface IMasterDokumenService {
	Dokumen save(Dokumen dokumen);
	Dokumen update(Dokumen dokumen);
	Dokumen updateById(String id, Dokumen dokumen);
	DeleteResponse delete(String Id);
	List<Dokumen> getDaftarMasterDokumen(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
