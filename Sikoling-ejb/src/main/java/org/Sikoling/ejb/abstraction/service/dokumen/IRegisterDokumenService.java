package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;

public interface IRegisterDokumenService {
	RegisterDokumen save(RegisterDokumen t) throws IOException;
	RegisterDokumen update(RegisterDokumen t);
	RegisterDokumen updateId(String idLama, RegisterDokumen t) throws IOException;
	RegisterDokumen delete(RegisterDokumen t, Otoritas userOtoritas) throws IOException; 
	List<RegisterDokumen> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
	RegisterDokumen getById(String id) throws IOException;
}
