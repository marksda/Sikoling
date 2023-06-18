package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;

public interface IStatusFlowLogRepository extends IRepository<StatusFlowLog> {
	StatusFlowLog updateId(String idLama, StatusFlowLog t) throws IOException;
}
