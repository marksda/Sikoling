package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;

public interface IPenanggungJawabRepository extends IRepository<PenanggungJawab> {
	PenanggungJawab updateId(String idLama, PenanggungJawab t) throws IOException;
}
