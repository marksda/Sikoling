package org.Sikoling.ejb.main.repository.perusahaan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pegawai;
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
	public List<Pegawai> getAllByPage(Integer page, Integer pageSize) {
		return pegawaiPerusahaanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Pegawai> getByNama(String nama) {
		return pegawaiPerusahaanRepository.getByNama(nama);
	}

	@Override
	public List<Pegawai> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		return pegawaiPerusahaanRepository.getByNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Pegawai> getByRegisterPerusahaan(String idRegisterPerusahaan) {
		return pegawaiPerusahaanRepository.getByRegisterPerusahaan(idRegisterPerusahaan);
	}

}
