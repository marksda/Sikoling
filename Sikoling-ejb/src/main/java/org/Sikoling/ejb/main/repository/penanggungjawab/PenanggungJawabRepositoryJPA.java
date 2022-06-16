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
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;

import jakarta.persistence.EntityManager;

public class PenanggungJawabRepositoryJPA implements IPenanggungJawabRepository {
	
	private final EntityManager entityManager;

	public PenanggungJawabRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public PenanggungJawab save(PenanggungJawab t) {
		PenanggungJawabData penanggungJawabData = convertPenanggungJawabToPenanggungJawabData(t);
		entityManager.persist(t);
		entityManager.flush();
		return convertPenanggungJawabDataToPenanggungJawab(penanggungJawabData);
	}

	@Override
	public PenanggungJawab update(PenanggungJawab t) {
		PenanggungJawabData penanggungJawabData = convertPenanggungJawabToPenanggungJawabData(t);
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

	private PenanggungJawabData convertPenanggungJawabToPenanggungJawabData(PenanggungJawab t) {
		PenanggungJawabData penanggungJawabData = new PenanggungJawabData();
		DesaData desaData = new DesaData();
		desaData.setId(t.getAlamat().getDesa().getId());
		KecamatanData kecamatanData= new KecamatanData();
		kecamatanData.setId(t.getAlamat().getKecamatan().getId());
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(t.getAlamat().getKabupaten().getId());
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(t.getAlamat().getPropinsi().getId());
		JabatanData jabatanData = new JabatanData();
		jabatanData.setId(t.getJabatan().getId());
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(t.getJenisKelamin().getId());
		
		penanggungJawabData.setId(t.getId());
		penanggungJawabData.setDesa(desaData);
		penanggungJawabData.setKecamatan(kecamatanData);
		penanggungJawabData.setKabupaten(kabupatenData);
		penanggungJawabData.setPropinsi(propinsiData);
		penanggungJawabData.setDetailAlamat(t.getAlamat().getKeterangan());
		penanggungJawabData.setJabatan(jabatanData);
		penanggungJawabData.setNama(t.getNama());
		penanggungJawabData.setNomorHandphone(t.getNoHandphone());
		penanggungJawabData.setNomorIdentitas(t.getNoIdentitas());
		penanggungJawabData.setSex(jenisKelaminData);
		
		return penanggungJawabData;
	}
	
	private PenanggungJawab convertPenanggungJawabDataToPenanggungJawab(PenanggungJawabData d) {
		PropinsiData propinsiData = d.getPropinsi();
		Propinsi propinsi = new Propinsi(propinsiData.getId());
		KabupatenData kabupatenData = d.getKabupaten();
		Kabupaten kabupaten = new Kabupaten(kabupatenData.getId());
		KecamatanData kecamatanData = d.getKecamatan();
		Kecamatan kecamatan = new Kecamatan(kecamatanData.getId());
		DesaData desaData = d.getDesa();
		Desa desa = new Desa(desaData.getId());		
		Alamat alamat = new Alamat(propinsi, kabupaten, kecamatan, desa, d.getDetailAlamat());
		JabatanData jabatanData = d.getJabatan();
		Jabatan jabatan = new Jabatan(jabatanData.getId());
		JenisKelaminData sexData = d.getSex();
		JenisKelamin sex = new JenisKelamin(sexData.getId());
		PenanggungJawab penanggungJawab = new PenanggungJawab(d.getId(), d.getNama(), alamat, 
				jabatan, sex, d.getNomorIdentitas(), d.getNomorHandphone());
		
		return penanggungJawab;
				
	}

}
