package org.Sikoling.ejb.abstraction.service.sex;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;

public interface IJenisKelaminService {
	JenisKelamin save(JenisKelamin jenisKelamin);
	JenisKelamin update(JenisKelamin jenisKelamin);
	List<JenisKelamin> getAll();
	List<JenisKelamin> getAllByPage(Integer page, Integer pageSize);
	List<JenisKelamin> getByQueryNama(String nama);
	List<JenisKelamin> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize);
}
