package org.Sikoling.ejb.abstraction.service.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;

public class FlowLogService implements IFlowLogService {
	
	private final IFlowLogRepository flowLogRepository;

	public FlowLogService(IFlowLogRepository flowLogRepository) {
		this.flowLogRepository = flowLogRepository;
	}

	@Override
	public DeleteResponse delete(String id) {
		return flowLogRepository.delete(id);
	}

	@Override
	public FlowLog save(FlowLog t) {
		return flowLogRepository.save(t);
	}

	@Override
	public FlowLog update(FlowLog t) {
		return flowLogRepository.update(t);
	}

	@Override
	public List<FlowLog> getAll() {
		return flowLogRepository.getAll();
	}

	@Override
	public List<FlowLog> getAllByPage(Integer page, Integer pageSize) {
		return flowLogRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<FlowLog> getByIdPengakses(String idPengakses) {
		return flowLogRepository.getByIdPengakses(idPengakses);
	}

	@Override
	public List<FlowLog> getByIdKategori(String idKategoriLog) {
		return flowLogRepository.getByIdKategori(idKategoriLog);
	}

}
