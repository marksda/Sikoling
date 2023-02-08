package org.Sikoling.ejb.main.repository.log;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;

public class FlowLogRepositoryJPA implements IFlowLogRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public FlowLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<FlowLog> getAll() {
		return entityManager.createNamedQuery("FlowLogData.findAll", FlowLogData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertFlowLogDataToFlowLog(d))
				.collect(Collectors.toList());
	}

	@Override
	public FlowLog save(FlowLog t) {
		FlowLogData flowLogData = dataConverter.convertFlowLogToFlowLogData(t);
		entityManager.persist(flowLogData);
		entityManager.flush();
		return dataConverter.convertFlowLogDataToFlowLog(flowLogData);
	}

	@Override
	public FlowLog update(FlowLog t) {
		FlowLogData flowLogData = dataConverter.convertFlowLogToFlowLogData(t);
		flowLogData = entityManager.merge(flowLogData);
		return dataConverter.convertFlowLogDataToFlowLog(flowLogData);
	}

	@Override
	public DeleteResponse delete(String id) {
		FlowLogData flowLogData = entityManager.find(FlowLogData.class, id);
		entityManager.remove(flowLogData);			
		return new DeleteResponse(true, id);
	}

	@Override
	public List<FlowLog> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("FlowLogData.findAll", FlowLogData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertFlowLogDataToFlowLog(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlowLog> getByIdPengakses(String idPengakses) {
		return entityManager.createNamedQuery("FlowLogData.findByIdPengakses", FlowLogData.class)
				.setParameter("idPengakses", idPengakses)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertFlowLogDataToFlowLog(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlowLog> getByIdKategori(String idKategoriLog) {
		return entityManager.createNamedQuery("FlowLogData.findByIdKategoriLog", FlowLogData.class)
				.setParameter("idKategoriLog", idKategoriLog)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertFlowLogDataToFlowLog(d))
				.collect(Collectors.toList());
	}
	
}
