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
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
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
//		return convertPersonDataToPerson(personData);
		return convertPersonDataToPerson(entityManager.find(PersonData.class, personData.getId()));
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

	@Override
	public Person getByNik(String nik) {
		try {
			PersonData hasil = entityManager.createNamedQuery("PersonData.findById", PersonData.class)
					.setParameter("nik", nik)
					.getSingleResult();
			return convertPersonDataToPerson(hasil);
		} catch (Exception e) {
			return null;
		}
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
		
		AlamatData alamatPersonData = new AlamatData();
		alamatPersonData.setPropinsi(propinsiData);
		alamatPersonData.setKabupaten(kabupatenData);
		alamatPersonData.setKecamatan(kecamatanData);
		alamatPersonData.setDesa(desaData);
		alamatPersonData.setDetailAlamat(person.getAlamat().getKeterangan());
		
		personData.setAlamat(alamatPersonData);
		
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(person.getSex().getId());
		personData.setSex(jenisKelaminData);
		
		KontakData kontakPersonData = new KontakData();
		kontakPersonData.setTelepone(person.getKontak().getTelepone());
		kontakPersonData.setEmail(person.getKontak().getEmail());
		personData.setKontak(kontakPersonData);
		
		personData.setScanKtp(person.getScanKTP());
		
		return personData;
	}
	
	private Propinsi convertPropinsiDataToPropinsi(PropinsiData d) {
		Propinsi propinsi = null;
		
		if(d != null) {
			propinsi = new Propinsi(d.getId(), d.getNama());
		}
		
		return propinsi;		
	}
	
	private Kabupaten convertKabupatenDataToKabupaten(KabupatenData d) {
		Kabupaten kabupaten = null;
		
		if(d != null) {
			kabupaten = new Kabupaten(d.getId(), d.getNama());
		}
		
		return kabupaten;		
	}

	private Kecamatan convertKecamatanDataToKecamatan(KecamatanData d) {
		Kecamatan kecamatan = null;
		
		if(d != null) {
			kecamatan = new Kecamatan(d.getId(), d.getNama());
		}
		
		return kecamatan;		
	}
	
	private Desa convertDesaDataToDesa(DesaData d) {
		Desa desa = null;
		
		if(d != null) {
			desa = new Desa(d.getId(), d.getNama());
		}
		
		return desa;		
	}
	
	private Alamat convertAlamatDataToAlamat(AlamatData d) {
		Alamat alamat = null;
		
		if( d != null) {
			alamat = new Alamat(
					convertPropinsiDataToPropinsi(d.getPropinsi()), 
					convertKabupatenDataToKabupaten(d.getKabupaten()), 
					convertKecamatanDataToKecamatan(d.getKecamatan()), 
					convertDesaDataToDesa(d.getDesa()), 
					d.getDetailAlamat()
					);					
		}
		
		return alamat;
	}	
	
	private Kontak convertKontakDataToKontak(KontakData d) {
		Kontak kontak = null;
		
		if(d != null) {
			kontak = new Kontak(
					d.getTelepone(), 
					d.getFax(), 
					d.getEmail()
					);
		}
		
		return kontak;
	}
	
	private Person convertPersonDataToPerson(PersonData d) {
		Person person = null;
		
		if(d != null) {
			JenisKelaminData jenisKelaminData = d.getSex();
			JenisKelamin jenisKelamin = jenisKelaminData != null ?
					new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama()) : null;
			
			Alamat alamat = convertAlamatDataToAlamat(d.getAlamat());
			
			Kontak kontak = convertKontakDataToKontak(d.getKontak());
			
			person = new Person(
					d.getId(), 
					d.getNama(), 
					jenisKelamin, 
					alamat, 
					d.getScanKtp(), 
					kontak
					);
		}
		
		return person;
	}

	
}
