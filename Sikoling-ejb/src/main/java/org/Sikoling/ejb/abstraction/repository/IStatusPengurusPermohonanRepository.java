package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;

public interface IStatusPengurusPermohonanRepository extends IRepository<StatuswaliPermohonan> {
	StatuswaliPermohonan updateId(String idLama, StatuswaliPermohonan t) throws IOException;
}
