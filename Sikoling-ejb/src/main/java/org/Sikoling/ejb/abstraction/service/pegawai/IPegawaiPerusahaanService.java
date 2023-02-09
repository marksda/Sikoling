package org.Sikoling.ejb.abstraction.service.pegawai;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pegawai;

public interface IPegawaiPerusahaanService {
	Pegawai save(Pegawai pegawai);
	Pegawai update(Pegawai pegawai);
	List<Pegawai> getAll();
	List<Pegawai> getAllByPage(Integer page, Integer pageSize);
	List<Pegawai> getByNama(String nama);
	List<Pegawai> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Pegawai> getByRegisterPerusahaan(String idRegisterPerusahaan);	
}
