package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
@LocalBean
@Infrastructure
public class PosisiTahapPemberkasanRepositoryEJB implements IPosisiTahapPemberkasanRepository {
	
	@Inject
	private PosisiTahapPemberkasanRepositoryJPA posisiTahapPemberkasanRepository;

	@Override
	public List<PosisiTahapPemberkasan> getAll() {
		return posisiTahapPemberkasanRepository.getAll();
	}

	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.save(t);
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		return posisiTahapPemberkasanRepository.update(t);
	}

	@Override
	public DeleteResponse delete(String id) {
		return posisiTahapPemberkasanRepository.delete(id);
	}

}
