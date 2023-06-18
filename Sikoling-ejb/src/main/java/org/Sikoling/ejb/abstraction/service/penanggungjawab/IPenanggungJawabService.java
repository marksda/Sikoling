package org.Sikoling.ejb.abstraction.service.penanggungjawab;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IPenanggungJawabService {
	PenanggungJawab save(PenanggungJawab t) throws IOException;
	PenanggungJawab update(PenanggungJawab t);
	PenanggungJawab updateId(String idLama, PenanggungJawab t) throws IOException;
	PenanggungJawab delete(PenanggungJawab t) throws IOException;
	List<PenanggungJawab> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
