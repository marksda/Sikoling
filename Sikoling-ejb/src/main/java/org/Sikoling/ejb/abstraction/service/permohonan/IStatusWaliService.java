package org.Sikoling.ejb.abstraction.service.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;

public interface IStatusWaliService {
	DeleteResponse delete(String id);
	StatusWali save(StatusWali statusWali);
	StatusWali update(StatusWali statusWali);
	List<StatusWali> getAll();
	List<StatusWali> getAllByPage(Integer page, Integer pageSize);
}
