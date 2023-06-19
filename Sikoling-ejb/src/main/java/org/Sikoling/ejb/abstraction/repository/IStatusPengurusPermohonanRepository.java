package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusPengurusPermohonan;

public interface IStatusPengurusPermohonanRepository extends IRepository<StatusPengurusPermohonan> {
	StatusPengurusPermohonan updateId(String idLama, StatusPengurusPermohonan t) throws IOException;
}
