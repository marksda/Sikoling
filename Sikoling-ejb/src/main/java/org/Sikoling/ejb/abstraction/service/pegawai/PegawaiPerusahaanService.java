package org.Sikoling.ejb.abstraction.service.pegawai;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pegawai;
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
	public List<Pegawai> getAll() {
		return pegawaiPerusahaanRepository.getAll();
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
