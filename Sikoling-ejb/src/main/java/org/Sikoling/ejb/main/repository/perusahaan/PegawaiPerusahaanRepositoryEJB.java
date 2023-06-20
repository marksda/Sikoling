package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.IOException;
import java.util.List;

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
	public Pegawai save(Pegawai t) throws IOException {
		return pegawaiPerusahaanRepository.save(t);
	}

	@Override
	public Pegawai update(Pegawai t) {
		return pegawaiPerusahaanRepository.update(t);
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

	@Override
	public Pegawai updateId(String idLama, Pegawai t) throws IOException {
		return pegawaiPerusahaanRepository.updateId(idLama, t);
	}
	
}
