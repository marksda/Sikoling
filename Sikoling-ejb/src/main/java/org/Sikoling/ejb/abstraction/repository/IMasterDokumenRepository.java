package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;

public interface IMasterDokumenRepository extends IRepository<Dokumen> {
	DeleteResponse delete(String id);
	Dokumen updateById(String id, Dokumen dokumen);
	List<Dokumen> getDaftarMasterDokumen(QueryParamFilters queryParamFilters);
	Long getCount(List<Filter> queryParamFilters);
}
