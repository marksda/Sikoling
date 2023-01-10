package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.permohonan.JenisPermohonanSuratArahan;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanSuratArahahanRepository;

import jakarta.persistence.EntityManager;

public class KategoriPermohonanSuratArahanRepositoryJPA implements IKategoriPermohonanSuratArahahanRepository {
	
	private final EntityManager entityManager;		

	public KategoriPermohonanSuratArahanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<JenisPermohonanSuratArahan> getAll() {
		return entityManager.createNamedQuery("KategoriPermohonanSuratArahanData.findAll", KategoriPermohonanSuratArahanData.class)
				.getResultList()
				.stream()
				.map(d -> convertKategoriPermohonanSuratArahanDataToJenisPermohonanSuratArahan(d))
				.collect(Collectors.toList());
	}

	@Override
	public JenisPermohonanSuratArahan save(JenisPermohonanSuratArahan t) {
		KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = convertJenisPermohonanSuratArahanToKategoriPermohonanSuratArahanData(t);
		entityManager.persist(kategoriPermohonanSuratArahanData);
		entityManager.flush();
		
		return convertKategoriPermohonanSuratArahanDataToJenisPermohonanSuratArahan(
				entityManager.find(KategoriPermohonanSuratArahanData.class, kategoriPermohonanSuratArahanData.getId())
				);
	}

	@Override
	public JenisPermohonanSuratArahan update(JenisPermohonanSuratArahan t) {
		KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = convertJenisPermohonanSuratArahanToKategoriPermohonanSuratArahanData(t);
		kategoriPermohonanSuratArahanData = entityManager.merge(kategoriPermohonanSuratArahanData);
		
		return convertKategoriPermohonanSuratArahanDataToJenisPermohonanSuratArahan(
				entityManager.find(KategoriPermohonanSuratArahanData.class, kategoriPermohonanSuratArahanData.getId())
				);
	}

	@Override
	public DeleteResponse delete(String id) {
		KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = entityManager.find(KategoriPermohonanSuratArahanData.class, id);
		entityManager.remove(kategoriPermohonanSuratArahanData);
		return new DeleteResponse(true, id);
	}

	private JenisPermohonanSuratArahan convertKategoriPermohonanSuratArahanDataToJenisPermohonanSuratArahan(KategoriPermohonanSuratArahanData d) {
		return new JenisPermohonanSuratArahan(d.getId(), d.getKeterangan());
	}
	
	private KategoriPermohonanSuratArahanData convertJenisPermohonanSuratArahanToKategoriPermohonanSuratArahanData(JenisPermohonanSuratArahan t) {
		KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = new KategoriPermohonanSuratArahanData();
		kategoriPermohonanSuratArahanData.setId(t.getId());
		kategoriPermohonanSuratArahanData.setKeterangan(t.getKeterangan());
		
		return kategoriPermohonanSuratArahanData;
	}
	
}
