package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;

public interface IKategoriLogRepository extends IRepository<KategoriFlowLog> {
	KategoriFlowLog updateId(String idLama, KategoriFlowLog t) throws IOException;
}