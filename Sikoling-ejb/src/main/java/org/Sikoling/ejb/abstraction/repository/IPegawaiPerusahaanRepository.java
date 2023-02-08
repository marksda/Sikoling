package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Pegawai;

public interface IPegawaiPerusahaanRepository extends IRepository<Pegawai> {
	List<Pegawai> getAllByPage(Integer page, Integer pageSize);
	List<Pegawai> getByNama(String nama);
	List<Pegawai> getByNamaAndPage(String nama, Integer page, Integer pageSize);
	List<Pegawai> getByRegisterPerusahaan(String idRegisterPerusahaan);
	
}
