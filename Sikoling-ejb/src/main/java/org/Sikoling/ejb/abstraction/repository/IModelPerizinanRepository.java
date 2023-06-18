package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;

public interface IModelPerizinanRepository extends IRepository<ModelPerizinan> {
	ModelPerizinan updateId(String idLama, ModelPerizinan t) throws IOException;
}
