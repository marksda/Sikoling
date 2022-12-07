package org.Sikoling.ejb.main.repository.authority;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Autorisasi;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IAutorisasiRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.person.AlamatPersonData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;

import jakarta.persistence.EntityManager;

public class AutorisasiRepositoryJPA implements IAutorisasiRepository {
	
	private final EntityManager entityManager;

	public AutorisasiRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Autorisasi> getAll() {
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public Autorisasi save(Autorisasi t) {
		AutorisasiData autorisasiData = convertAutorisasiToAutorisasiData(t);
		entityManager.persist(autorisasiData);
		entityManager.flush();
		return convertAutorisasiDataToAutorisasi(autorisasiData);
	}

	@Override
	public Autorisasi update(Autorisasi t) {
		AutorisasiData autorisasiData = convertAutorisasiToAutorisasiData(t);
		autorisasiData = entityManager.merge(autorisasiData);
		return convertAutorisasiDataToAutorisasi(autorisasiData);
	}

	@Override
	public List<Autorisasi> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Autorisasi> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.setParameter("userName", nama)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Autorisasi> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	private AutorisasiData convertAutorisasiToAutorisasiData(Autorisasi t) {
		AutorisasiData autorisasiData = new AutorisasiData();
		
		PersonData personData = new PersonData();
		Person person = t.getPerson();
		
		personData.setId(person.getNik());		
		personData.setNama(person.getNama());
		
		AlamatPersonData alamatPersonData = new AlamatPersonData();
		Alamat alamat = person.getAlamat();
		
		PropinsiData propinsiData = new PropinsiData();
		Propinsi propinsi = alamat.getPropinsi();
		propinsiData.setId(propinsi.getId());
		propinsiData.setNama(propinsi.getNama());
		
		KabupatenData kabupatenData = new KabupatenData();
		Kabupaten kabupaten = alamat.getKabupaten();
		kabupatenData.setId(kabupaten.getId());
		kabupatenData.setNama(kabupaten.getNama());
		kabupatenData.setPropinsi(propinsiData);
		
		KecamatanData kecamatanData = new KecamatanData();
		Kecamatan kecamatan = alamat.getKecamatan();
		kecamatanData.setId(kecamatan.getId());
		kecamatanData.setNama(kecamatan.getNama());
		kecamatanData.setKabupaten(kabupatenData);
		
		DesaData desaData = new DesaData();
		Desa desa = alamat.getDesa();
		desaData.setId(desa.getId());
		desaData.setNama(desa.getNama());
		
		alamatPersonData.setPropinsi(propinsiData);
		alamatPersonData.setKabupaten(kabupatenData);
		alamatPersonData.setKecamatan(kecamatanData);
		alamatPersonData.setDesa(desaData);
		alamatPersonData.setDetailAlamat(alamat.getKeterangan());		
		
		personData.setAlamat(alamatPersonData);
		
		HakAksesData hakAksesData = new HakAksesData();
		HakAkses hakAkses = t.getHakAkses();
		hakAksesData.setId(hakAkses.getId());
		hakAksesData.setNama(hakAkses.getNama());
		hakAksesData.setKeterangan(hakAkses.getKeterangan());
		
//		autorisasiData.setId(t.getId());
		autorisasiData.setIdLama(t.getIdLama());
		autorisasiData.setPersonData(personData);
		autorisasiData.setIdLama(t.getIdLama());
		autorisasiData.setHakAkses(hakAksesData);
		autorisasiData.setStatusInternal(t.isStatusInternal());
		autorisasiData.setIsVerified(t.isVerified());
		autorisasiData.setUserName(t.getUserName());
		
		
		return autorisasiData;
	}
	private Autorisasi convertAutorisasiDataToAutorisasi(AutorisasiData d) {
//		AutorisasiData autorisasiData = entityManager.find(AutorisasiData.class, autorisasiData.getId());
		
		Person person = new Person(
				d.getPersonData().getId(), 
				d.getPersonData().getNama(), 
				new JenisKelamin(d.getPersonData().getSex().getId(), d.getPersonData().getSex().getNama()), 
				new Alamat(
						new Propinsi(
								d.getPersonData().getAlamat().getPropinsi().getId(), 
								d.getPersonData().getAlamat().getPropinsi().getNama()), 
						new Kabupaten(
								d.getPersonData().getAlamat().getKabupaten().getId(),
								d.getPersonData().getAlamat().getKabupaten().getNama()), 
						new Kecamatan(
								d.getPersonData().getAlamat().getKecamatan().getId(),
								d.getPersonData().getAlamat().getKecamatan().getNama()) , 
						new Desa(
								d.getPersonData().getAlamat().getDesa().getId(), 
								d.getPersonData().getAlamat().getDesa().getNama()), 
						d.getPersonData().getAlamat().getDetailAlamat()), 
				d.getPersonData().getScanKtp(), 
				new Kontak(
						d.getPersonData().getKontak().getTelepone(),
						null,
						d.getPersonData().getKontak().getEmail()));
		HakAkses hakAkses = new HakAkses(
				d.getHakAkses().getId(), 
				d.getHakAkses().getNama(), 
				d.getHakAkses().getKeterangan());
		
		return new Autorisasi(
				person.getNik(), person, d.getIdLama(), hakAkses, 
				d.getStatusInternal(), d.getIsVerified(), d.getUserName());
	}
}
