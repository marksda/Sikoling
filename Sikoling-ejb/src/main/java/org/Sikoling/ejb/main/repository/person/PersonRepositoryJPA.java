package org.Sikoling.ejb.main.repository.person;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;

import jakarta.persistence.EntityManager;

public class PersonRepositoryJPA implements IPersonRepository {
	
	private final EntityManager entityManager;

	public PersonRepositoryJPA(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Person save(Person t) {
		PersonData personData = convertPersonToPersonData(t);
		entityManager.persist(personData);
		entityManager.flush();
		return convertPersonDataToPerson(personData);
	}

	@Override
	public Person update(Person t) {
		PersonData personData = convertPersonToPersonData(t);
		personData = entityManager.merge(personData);
		return convertPersonDataToPerson(personData);
	}

	@Override
	public List<Person> getAll() {
		return entityManager.createNamedQuery("PersonData.findAll", PersonData.class)
				.getResultList()
				.stream()
				.map(t -> convertPersonDataToPerson(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PersonData.findAll", PersonData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPersonDataToPerson(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PersonData.findByNama", PersonData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPersonDataToPerson(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PersonData.findByNama", PersonData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPersonDataToPerson(t))
				.collect(Collectors.toList());
	}

	private PersonData convertPersonToPersonData(Person person) {
		PersonData personData = new PersonData();
		personData.setId(person.getNik());
		personData.setNama(person.getNama());
		
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(person.getAlamat().getPropinsi().getId());
		
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(person.getAlamat().getKabupaten().getId());
		
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(person.getAlamat().getKecamatan().getId());
		
		DesaData desaData = new DesaData();
		desaData.setId(person.getAlamat().getDesa().getId());
		
		AlamatPersonData alamatPersonData = new AlamatPersonData();
		alamatPersonData.setPropinsi(propinsiData);
		alamatPersonData.setKabupaten(kabupatenData);
		alamatPersonData.setKecamatan(kecamatanData);
		alamatPersonData.setDesa(desaData);
		alamatPersonData.setDetailAlamat(person.getAlamat().getKeterangan());
		
		personData.setAlamat(alamatPersonData);
		
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(person.getSex().getId());
		personData.setSex(jenisKelaminData);
		
		KontakPersonData kontakPersonData = new KontakPersonData();
		kontakPersonData.setTelepone(person.getKontak().getTelepone());
		kontakPersonData.setEmail(person.getKontak().getEmail());
		personData.setKontak(kontakPersonData);
		
		personData.setScanKtp(person.getScanKTP());
		
		return personData;
	}
	
	private Person convertPersonDataToPerson(PersonData personData) {
		PersonData data = entityManager.find(PersonData.class, personData.getId());		
		return new Person(
				data.getId(), data.getNama(), 
				new JenisKelamin(data.getSex().getId(), data.getSex().getNama()),
				new Alamat(
						new Propinsi(data.getAlamat().getPropinsi().getId(), data.getAlamat().getPropinsi().getNama()), 
						new Kabupaten(data.getAlamat().getKabupaten().getId(), data.getAlamat().getKabupaten().getNama()),
						new Kecamatan(data.getAlamat().getKecamatan().getId(), data.getAlamat().getKecamatan().getNama()), 
						new Desa(data.getAlamat().getDesa().getId(), data.getAlamat().getDesa().getNama()), 
						data.getAlamat().getDetailAlamat()), 
				data.getScanKtp(),
				new Kontak(data.getKontak().getTelepone(), null, data.getKontak().getEmail()));
	}
}
