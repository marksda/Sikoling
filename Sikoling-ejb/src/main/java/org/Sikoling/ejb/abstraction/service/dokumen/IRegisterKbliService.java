package org.Sikoling.ejb.abstraction.service.dokumen;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.RegisterKbli;

public interface IRegisterKbliService {
	RegisterKbli save(RegisterKbli t) throws IOException;
	RegisterKbli update(RegisterKbli t);	
	RegisterKbli updateId(String idNibLama, String idKbliLama, RegisterKbli t) throws IOException;
	RegisterKbli delete(RegisterKbli t) throws IOException;
	List<RegisterKbli> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
