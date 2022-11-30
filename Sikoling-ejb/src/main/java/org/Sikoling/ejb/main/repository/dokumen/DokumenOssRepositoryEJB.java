package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.repository.IDokumenOssRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@Local
@Infrastructure
public class DokumenOssRepositoryEJB implements IDokumenOssRepository {
	
	@Inject
	private RegisterDokumenOssRepositoryJPA dokumenOssRepository;

	@Override
	public List<DokumenOss> getAll() {
		return dokumenOssRepository.getAll();
	}

	@Override
	public DokumenOss save(DokumenOss t, String s) {
		return dokumenOssRepository.save(t, s);
	}

	@Override
	public DokumenOss update(DokumenOss t, String s) {
		return dokumenOssRepository.update(t, s);
	}

	@Override
	public DeleteResponse delete(String nib) {
		return dokumenOssRepository.delete(nib);
	}

	@Override
	public DokumenOss updateById(String nib, DokumenOss dokumenOss, String s) {
		return dokumenOssRepository.updateById(nib, dokumenOss, s);
	}

	@Override
	public List<DokumenOss> getAllByPage(Integer page, Integer pageSize) {
		return dokumenOssRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<DokumenOss> getByNib(String nib) {
		return dokumenOssRepository.getByNib(nib);
	}

	@Override
	public List<DokumenOss> getByNibAndPage(String nib, Integer page, Integer pageSize) {
		return dokumenOssRepository.getByNibAndPage(nib, page, pageSize);
	}

}
