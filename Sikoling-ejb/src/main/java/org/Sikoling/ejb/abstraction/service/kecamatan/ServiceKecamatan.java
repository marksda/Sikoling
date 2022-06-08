package org.Sikoling.ejb.abstraction.service.kecamatan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;

import jakarta.inject.Inject;

public class ServiceKecamatan implements IServiceKecamatan {
	
	@Inject
	private final IKecamatanRepository kecamatanRepository;	

	public ServiceKecamatan(IKecamatanRepository kecamatanRepository) {
		this.kecamatanRepository = kecamatanRepository;
	}

	@Override
	public Kecamatan save(Kecamatan kecamatan) {
		return kecamatanRepository.save(kecamatan);
	}

	@Override
	public Kecamatan update(Kecamatan kecamatan) {
		return kecamatanRepository.update(kecamatan);
	}

	@Override
	public List<Kecamatan> getAllByPage(Integer page, Integer pageSize) {
		return kecamatanRepository.getAllByPage(page, pageSize);
	}

	@Override
	public List<Kecamatan> getByQueryNama(String nama) {
		return kecamatanRepository.getByQueryNama(nama);
	}

	@Override
	public List<Kecamatan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		return kecamatanRepository.getByQueryNamaAndPage(nama, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupaten(String idKabupaten) {
		return kecamatanRepository.getByKabupaten(idKabupaten);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndPage(String idKabupaten, Integer page, Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndPage(idKabupaten, page, pageSize);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNama(String idKabupaten, String nama) {
		return kecamatanRepository.getByKabupatenAndQueryNama(idKabupaten, nama);
	}

	@Override
	public List<Kecamatan> getByKabupatenAndQueryNamaAndPage(String idKabupaten, String nama, Integer page,
			Integer pageSize) {
		return kecamatanRepository.getByKabupatenAndQueryNamaAndPage(idKabupaten, nama, page, pageSize);
	}

}
