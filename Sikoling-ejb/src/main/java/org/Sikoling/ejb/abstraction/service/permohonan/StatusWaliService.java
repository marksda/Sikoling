package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;
import org.Sikoling.ejb.abstraction.repository.IStatusWaliRepository;

public class StatusWaliService implements IStatusWaliService {
	
	private final IStatusWaliRepository statusWaliRepository;	

	public StatusWaliService(IStatusWaliRepository statusWaliRepository) {
		this.statusWaliRepository = statusWaliRepository;
	}

	@Override
	public DeleteResponse delete(String id) {
		return statusWaliRepository.delete(id);
	}

	@Override
	public StatusWali save(StatusWali statusWali) {
		return statusWaliRepository.save(statusWali);
	}

	@Override
	public StatusWali update(StatusWali statusWali) {
		return statusWaliRepository.update(statusWali);
	}

	@Override
	public List<StatusWali> getAll() {
		return statusWaliRepository.getAll();
	}

	@Override
	public List<StatusWali> getAllByPage(Integer page, Integer pageSize) {
		return statusWaliRepository.getAllByPage(page, pageSize);
	}

}
