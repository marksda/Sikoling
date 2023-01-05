package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;

public interface IFlowLogService {
	DeleteResponse delete(String id);
	FlowLog save(FlowLog t);
	FlowLog update(FlowLog t);
	List<FlowLog> getAll();
	List<FlowLog> getAllByPage(Integer page, Integer pageSize);
	List<FlowLog> getByIdPengakses(String idPengakses);
	List<FlowLog> getByIdKategori(String idKategoriLog);
}
