package org.Sikoling.ejb.abstraction.service.pegawai;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.repository.IPegawaiPerusahaanRepository;

public class PegawaiPerusahaanService implements IPegawaiPerusahaanService {
	
	private final IPegawaiPerusahaanRepository pegawaiPerusahaanRepository;

	public PegawaiPerusahaanService(IPegawaiPerusahaanRepository pegawaiPerusahaanRepository) {
		this.pegawaiPerusahaanRepository = pegawaiPerusahaanRepository;
	}

	@Override
	public Pegawai save(Pegawai pegawai) {
		return pegawaiPerusahaanRepository.save(pegawai);
	}

	@Override
	public Pegawai update(Pegawai pegawai) {
		return pegawaiPerusahaanRepository.update(pegawai);
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
