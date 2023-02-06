package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;

public interface IStatusWaliRepository extends IRepository<StatusWali> {
	DeleteResponse delete(String id);
	List<StatusWali> getAllByPage(Integer page, Integer pageSize);
}
