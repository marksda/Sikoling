package org.Sikoling.ejb.main.repository.authority;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;

import jakarta.persistence.EntityManager;

public class AutorisasiRepositoryJPA implements IAuthorityRepository {
	
	private final EntityManager entityManager;

	public AutorisasiRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Authority> getAll() {
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public Authority save(Authority t) {
		AutorisasiData autorisasiData = convertAutorisasiToAutorisasiData(t);
		entityManager.persist(autorisasiData);
		entityManager.flush();
		return convertAutorisasiDataToAutorisasi(autorisasiData);
	}

	@Override
	public Authority update(Authority t) {
		AutorisasiData autorisasiData = convertAutorisasiToAutorisasiData(t);
		autorisasiData = entityManager.merge(autorisasiData);
		return convertAutorisasiDataToAutorisasi(autorisasiData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		AutorisasiData autorisasiData = entityManager.find(AutorisasiData.class, id);
		entityManager.remove(autorisasiData);
		return new DeleteResponse(true, id);
	}
	
	@Override
	public List<Authority> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("AutorisasiData.findAll", AutorisasiData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Authority> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("AutorisasiData.findByNama", AutorisasiData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<Authority> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("AutorisasiData.findByNama", AutorisasiData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertAutorisasiDataToAutorisasi(d))
				.collect(Collectors.toList());
	}

	@Override
	public Authority getByUserName(String userName) {
		AutorisasiData data = Optional.ofNullable(
				entityManager.createNamedQuery("AutorisasiData.findByUserName", AutorisasiData.class)
				.setParameter("userName", userName).getSingleResult()
				)
				.orElse(null);
		return data != null ? convertAutorisasiDataToAutorisasi(data):null;				
	}
	
	private AutorisasiData convertAutorisasiToAutorisasiData(Authority t) {
		AutorisasiData autorisasiData = new AutorisasiData();
		
		PersonData personData = new PersonData();
		Person person = t.getPerson();
		
		personData.setId(person.getNik());		
		personData.setNama(person.getNama());
		
		AlamatData alamatPersonData = new AlamatData();
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
		

		autorisasiData.setPersonData(personData);
		autorisasiData.setHakAkses(hakAksesData);
		autorisasiData.setStatusInternal(t.isStatusInternal());
		autorisasiData.setIsVerified(t.isVerified());
		autorisasiData.setUserName(t.getUserName());
		
		
		return autorisasiData;
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
	
	private HakAkses convertHakAksesDataToHakAkses(HakAksesData d) {
		HakAkses hakAkses = null;
				
		if( d != null) {
			hakAkses = new HakAkses(d.getId(), d.getNama(), d.getKeterangan());
		}
		
		return hakAkses;
	}
	
	private Authority convertAutorisasiDataToAuthority(AutorisasiData d) {
		Authority authority = null;
		Person person = convertPersonDataToPerson(d.getPersonData());
		HakAkses hakAkses = convertHakAksesDataToHakAkses(d.getHakAkses());
		
		if(d != null) {
			authority = new Authority(
					d.getId(), 
					person, 
					hakAkses, 
					d.getStatusInternal(), 
					d.getIsVerified(), 
					d.getUserName()
					);
		}
		
		return authority;
	}
	
	private Authority convertAutorisasiDataToAutorisasi(AutorisasiData d) {
		
		Authority authority = convertAutorisasiDataToAuthority(d);		
		return authority;
	}

	
}
