package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;
import org.Sikoling.ejb.abstraction.repository.IStatusWaliRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class StatusPengurusPermohonanRepositoryEJB implements IStatusWaliRepository {
	
	@Inject
	private StatusPengurusPermohonanRepositoryJPA statusPengurusPermohonanRepository;
	
	@Override
	public List<StatusWali> getAll() {
		return statusPengurusPermohonanRepository.getAll();
	}

	@Override
	public StatusWali save(StatusWali t) {
		return statusPengurusPermohonanRepository.save(t);
	}

	@Override
	public StatusWali update(StatusWali t) {
		return statusPengurusPermohonanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return statusPengurusPermohonanRepository.delete(id);
	}

	@Override
	public List<StatusWali> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
