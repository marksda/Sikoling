package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;

import jakarta.persistence.EntityManager;

public class PosisiTahapPemberkasanRepositoryJPA implements IPosisiTahapPemberkasanRepository {
	
	private final EntityManager entityManager;
	private final DataConverter dataConverter;

	public PosisiTahapPemberkasanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	

	@Override
	public List<PosisiTahapPemberkasan> getAll() {
		return entityManager.createNamedQuery("PosisiTahapPemberkasanData.findAll", PosisiTahapPemberkasanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d))
				.collect(Collectors.toList());
	}

	@Override
	public PosisiTahapPemberkasan save(PosisiTahapPemberkasan t) {
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = dataConverter.convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t);
		entityManager.persist(posisiTahapPemberkasanData);
		entityManager.flush();
		
		return dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(posisiTahapPemberkasanData);
	}

	@Override
	public PosisiTahapPemberkasan update(PosisiTahapPemberkasan t) {
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = dataConverter.convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t);
		posisiTahapPemberkasanData = entityManager.merge(posisiTahapPemberkasanData);
		return dataConverter.convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(posisiTahapPemberkasanData);
	}

	@Override
	public DeleteResponse delete(String id) {
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = entityManager.find(PosisiTahapPemberkasanData.class, id);
		entityManager.remove(posisiTahapPemberkasanData);			
		return new DeleteResponse(true, id);
	}

}
