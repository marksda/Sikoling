package org.Sikoling.ejb.main.repository.log;

import java.io.IOException;
import java.util.List;

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
	public KategoriFlowLog save(KategoriFlowLog t) throws IOException {
		return kategoriLogRepository.save(t);
	}

	@Override
	public KategoriFlowLog update(KategoriFlowLog t) {
		return kategoriLogRepository.update(t);
	}

	@Override
	public KategoriFlowLog delete(KategoriFlowLog t) throws IOException {
		return kategoriLogRepository.delete(t);
	}

	@Override
	public List<KategoriFlowLog> getDaftarData(QueryParamFilters queryParamFilters) {
		return kategoriLogRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return kategoriLogRepository.getJumlahData(queryParamFilters);
	}

	@Override
	public KategoriFlowLog updateId(String idLama, KategoriFlowLog t) throws IOException {
		return kategoriLogRepository.updateId(idLama, t);
	}
	
}
