package org.Sikoling.ejb.main.repository.log;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.repository.IKategoriLogRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class KategoriLogRepositoryEJB implements IKategoriLogRepository {
	
	@Inject
	private KategoriLogRepositoryJPA kategoriLogRepository;

	@Override
	public List<KategoriFlowLog> getAll() {
		return kategoriLogRepository.getAll();
	}

	@Override
	public KategoriFlowLog save(KategoriFlowLog t) {
		return kategoriLogRepository.save(t);
	}

	@Override
	public KategoriFlowLog update(KategoriFlowLog t) {
		return kategoriLogRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return kategoriLogRepository.delete(id);
	}

	@Override
	public List<KategoriFlowLog> getDaftarKategoriLog(QueryParamFilters queryParamFilters) {
		return kategoriLogRepository.getDaftarKategoriLog(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return kategoriLogRepository.getCount(queryParamFilters);
	}

}
