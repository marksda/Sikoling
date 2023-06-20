package org.Sikoling.ejb.abstraction.service.pegawai;

import java.io.IOException;
import java.util.List;

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
	public Pegawai save(Pegawai t) throws IOException {
		return pegawaiPerusahaanRepository.save(t);
	}

	@Override
	public Pegawai update(Pegawai t) {
		return pegawaiPerusahaanRepository.update(t);
	}

	@Override
	public Pegawai updateId(String idLama, Pegawai t) throws IOException {
		return pegawaiPerusahaanRepository.updateId(idLama, t);
	}

	@Override
	public Pegawai delete(Pegawai t) throws IOException {
		return pegawaiPerusahaanRepository.delete(t);
	}

	@Override
	public List<Pegawai> getDaftarData(QueryParamFilters queryParamFilters) {
		return pegawaiPerusahaanRepository.getDaftarData(queryParamFilters);
	}

	@Override
	public Long getJumlahData(List<Filter> queryParamFilters) {
		return pegawaiPerusahaanRepository.getJumlahData(queryParamFilters);
	}
	
}
