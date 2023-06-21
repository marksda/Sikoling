package org.Sikoling.ejb.abstraction.service.sex;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IJenisKelaminService {
	JenisKelamin save(JenisKelamin t) throws IOException;
	JenisKelamin update(JenisKelamin t);
	JenisKelamin updateId(String idLama, JenisKelamin t) throws IOException;
	JenisKelamin delete(JenisKelamin t) throws IOException;
	List<JenisKelamin> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
