package org.Sikoling.ejb.main.repository.sex;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class JenisKelaminRepositoryEJB implements IJenisKelaminRepository {
	
	@Inject
	private JenisKelaminRepositoryJPA jenisKelaminRepositoryJPA;

	@Override
	public List<JenisKelamin> getAll() {
		return jenisKelaminRepositoryJPA.getAll();
	}

	@Override
	public JenisKelamin save(JenisKelamin t) {
		return jenisKelaminRepositoryJPA.save(t);
	}

	@Override
	public JenisKelamin update(JenisKelamin t) {
		return jenisKelaminRepositoryJPA.update(t);
	}

	@Override
	public List<JenisKelamin> getAllByPage(Integer page, Integer pageSize) {
		return jenisKelaminRepositoryJPA.getAllByPage(page, pageSize);
	}

	@Override
	public List<JenisKelamin> getByQueryNama(String nama) {
		return jenisKelaminRepositoryJPA.getByQueryNama(nama);
	}

	@Override
	public List<JenisKelamin> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return jenisKelaminRepositoryJPA.getByQueryNamaAndPage(nama, page, pageSize);
	}

}
