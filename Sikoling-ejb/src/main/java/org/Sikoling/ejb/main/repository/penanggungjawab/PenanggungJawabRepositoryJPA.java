package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.pemrakarsa.PemrakarsaData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;

import jakarta.persistence.EntityManager;

public class PenanggungJawabRepositoryJPA implements IPenanggungJawabRepository {
	
	private final EntityManager entityManager;

	public PenanggungJawabRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public PenanggungJawab save(PenanggungJawab t, String s) {
		PenanggungJawabData penanggungJawabData = convertPenanggungJawabToPenanggungJawabData(t, s);
		entityManager.persist(t);
		entityManager.flush();
		return convertPenanggungJawabDataToPenanggungJawab(penanggungJawabData);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t, String s) {
		PenanggungJawabData penanggungJawabData = convertPenanggungJawabToPenanggungJawabData(t, s);
		penanggungJawabData = entityManager.merge(penanggungJawabData);
		return convertPenanggungJawabDataToPenanggungJawab(penanggungJawabData);
	}

	@Override
	public List<PenanggungJawab> getAll() {
		return entityManager.createNamedQuery("PenanggungJawabData.findAll", PenanggungJawabData.class)
				.getResultList()
				.stream()
				.map(t -> convertPenanggungJawabDataToPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PenanggungJawab> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PenanggungJawabData.findAll", PenanggungJawabData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPenanggungJawabDataToPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PenanggungJawab> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PenanggungJawabData.findByNama", PenanggungJawabData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPenanggungJawabDataToPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<PenanggungJawab> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PenanggungJawabData.findByNama", PenanggungJawabData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPenanggungJawabDataToPenanggungJawab(t))
				.collect(Collectors.toList());
	}

	private PenanggungJawabData convertPenanggungJawabToPenanggungJawabData(PenanggungJawab t, String idPemrakarsa) {
		PenanggungJawabData penanggungJawabData = new PenanggungJawabData();
		PersonData personData = new PersonData();
		personData.setId(t.getNoIdentitas());
		penanggungJawabData.setPerson(personData);
		PemrakarsaData pemrakarsaData = new PemrakarsaData();
		pemrakarsaData.setId(idPemrakarsa);
		penanggungJawabData.setPemrakarsa(pemrakarsaData);
		JabatanData jabatanData = new JabatanData();
		jabatanData.setId(t.getJabatan().getId());
		penanggungJawabData.setJabatan(jabatanData);
		
		return penanggungJawabData;
	}
	
	private PenanggungJawab convertPenanggungJawabDataToPenanggungJawab(PenanggungJawabData d) {	
		PenanggungJawabData penanggungJawabData = entityManager.find(PenanggungJawabData.class, d.getId());
		AlamatPersonData alamatPersonData = penanggungJawabData.getPerson().getAlamat();
		JenisKelaminData jenisKelaminData = penanggungJawabData.getPerson().getSex();
		
		return new PenanggungJawab(
				d.getId(), d.getPerson().getNama(),
				new Alamat(
						new Propinsi(alamatPersonData.getPropinsi().getId(), alamatPersonData.getPropinsi().getId()),
						new Kabupaten(alamatPersonData.getKabupaten().getId(), alamatPersonData.getKabupaten().getNama()),
						new Kecamatan(alamatPersonData.getKecamatan().getId(), alamatPersonData.getKecamatan().getNama()),
						new Desa(alamatPersonData.getDesa().getId(), alamatPersonData.getDesa().getNama()),
						alamatPersonData.getDetailAlamat()
						),
				new Jabatan(penanggungJawabData.getJabatan().getId(), penanggungJawabData.getJabatan().getNama()), 
				new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama()), 
				penanggungJawabData.getPerson().getId(), penanggungJawabData.getPerson().getTelepone()
				);				
	}

}
