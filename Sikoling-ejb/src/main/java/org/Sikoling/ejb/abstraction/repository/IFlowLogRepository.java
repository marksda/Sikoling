package org.Sikoling.ejb.abstraction.repository;

import java.io.IOException;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;

public interface IFlowLogRepository extends IRepository<FlowLog> {
	FlowLog updateId(String idLama, FlowLog t) throws IOException;
}
