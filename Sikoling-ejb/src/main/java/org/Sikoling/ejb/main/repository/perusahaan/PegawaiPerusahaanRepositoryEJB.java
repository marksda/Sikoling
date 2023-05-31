package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPegawaiPerusahaanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PegawaiPerusahaanRepositoryEJB implements IPegawaiPerusahaanRepository {
	
	@Inject
	private PegawaiPerusahaanRepositoryJPA pegawaiPerusahaanRepository;

	@Override
	public List<Pegawai> getAll() {
		return pegawaiPerusahaanRepository.getAll();
	}

	@Override
	public Pegawai save(Pegawai t) {
		return pegawaiPerusahaanRepository.save(t);
	}

	@Override
	public Pegawai update(Pegawai t) {
		return pegawaiPerusahaanRepository.update(t);
	}

	
	@Override
	public Pegawai updateById(String id, Pegawai pegawai) {
		return pegawaiPerusahaanRepository.updateById(id, pegawai);
	}

	
	@Override
	public DeleteResponse delete(String id) {
		return pegawaiPerusahaanRepository.delete(id);
	}

	
	@Override
	public List<Pegawai> getDaftarPegawai(QueryParamFilters queryParamFilters) {
		return pegawaiPerusahaanRepository.getDaftarPegawai(queryParamFilters);
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		return pegawaiPerusahaanRepository.getCount(queryParamFilters);
	}

}
