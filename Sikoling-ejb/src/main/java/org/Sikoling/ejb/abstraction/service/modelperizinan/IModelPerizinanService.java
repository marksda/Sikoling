package org.Sikoling.ejb.abstraction.service.modelperizinan;

import java.io.IOException;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public interface IModelPerizinanService {
	ModelPerizinan save(ModelPerizinan t) throws IOException;
	ModelPerizinan update(ModelPerizinan t);
	ModelPerizinan updateId(String idLama, ModelPerizinan t) throws IOException;
	ModelPerizinan delete(ModelPerizinan t) throws IOException;
	List<ModelPerizinan> getDaftarData(QueryParamFilters queryParamFilters);
	Long getJumlahData(List<Filter> queryParamFilters);
}
